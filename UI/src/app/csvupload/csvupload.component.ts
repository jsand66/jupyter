import { Component, OnInit } from '@angular/core';
import { environment } from '../../environments/environment';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { DomSanitizer } from '@angular/platform-browser';
import { URLSearchParams } from '@angular/http';
@Component({
  selector: 'app-csvupload',
  templateUrl: './csvupload.component.html',
  styleUrls: ['./csvupload.component.css']
})
export class CsvuploadComponent implements OnInit {
  height: any;

  port: any;
  loading = false;
  iframe_url: any;
  binaryString: any;
  filename: any;
  constructor(private http: Http, private sanitizer: DomSanitizer) { }

  ngOnInit() {
    this.start_jupyter();
  }
  openFile(event) {
    var files = event.target.files;
    var file = files[0];

    if (files && file) {
      let thisvar = this;
      var reader = new FileReader();
      reader.onload = function (readerEvt: any) {
        thisvar.binaryString = readerEvt.target.result;
        thisvar.filename = file.name;
      };
      reader.readAsBinaryString(file);
    }
  }
  uploadCSV() {
    if (localStorage.getItem("container_name") != undefined) {

      var requestData = { 'data': btoa(this.binaryString), 'filename': this.filename, 'container_id': localStorage.getItem("container_id") };
      this.upload_service(requestData).then((data: any) => {
      })
    } else {
      alert("Please Start Jupyter and Upload File")
    }
  }
  stopJupyter(){
    this.loading=true;
    var requestData = { 'container_id': localStorage.getItem("container_id")};
    this.stop_jupyter_service(requestData).then((data) => {
      if(data!=undefined)
      {
        alert(data)
        localStorage.removeItem('container_name');
        localStorage.removeItem('port');
        this.iframe_url=undefined;
        this.port=undefined;
        this.loading=false;
      }
      
    })
  }
  start_jupyter() {
    this.loading = true;
    if (localStorage.getItem("container_name") == undefined) {
      this.start_jupyter_service().then((data: any) => {
        if (data != undefined) {
        setTimeout(()=> {
          this.port = data.port;
          localStorage.removeItem('container_name');
          localStorage.removeItem('port');
          localStorage.setItem("container_name", data.container_name);
          localStorage.setItem("container_id", data.container_id);
          localStorage.setItem("port", data.port);
          let url = environment.ip + data.port + "/tree"
          this.iframe_url = this.sanitizer.bypassSecurityTrustResourceUrl(url)
          this.loading = false;
        }, 12000);
        }
      })
    }
    else {
      alert(localStorage.getItem("container_name") )
      this.port = localStorage.getItem("port")
      let url = environment.ip + this.port  + "/tree"
      this.iframe_url = this.sanitizer.bypassSecurityTrustResourceUrl(url)
      this.loading = false;
    }
    this.height = window.innerHeight - 120;
  }

  start_jupyter_service() {
    return new Promise(
      (resolve, reject) => {
        return this.http.get(environment.ip + environment.apiendpoint + 'startJupyter')
          .subscribe(res => {
            const data: any = res.json();
            resolve(data);
          },
          (err) => {
            reject(err);
            // ////this.congnitoUtil.refresh();
          });
      });
  }
  upload_service(usrstr) {
    let urlSearchParams = new URLSearchParams();
    urlSearchParams.append('data', usrstr.data);
    urlSearchParams.append('filename', usrstr.filename);
    urlSearchParams.append('container_id', usrstr.container_id);
    return new Promise(
      (resolve, reject) => {
        return this.http.post(environment.ip + environment.apiendpoint + 'uploadCSV', urlSearchParams)
          .subscribe(res => {
            const data: any = res;
            resolve(data);
          },
          (err) => {
            reject(err);
            // ////this.congnitoUtil.refresh();
          });
      });
  }
  stop_jupyter_service(requestData) {
    let urlSearchParams = new URLSearchParams();
    urlSearchParams.append('container_id', requestData.container_id);
    return new Promise(
      (resolve, reject) => {
        return this.http.post(environment.ip + environment.apiendpoint + 'stopJupyter',urlSearchParams)
          .subscribe(res => {
            const data: any = res
            resolve(data);
          },
          (err) => {
            reject(err);
            // ////this.congnitoUtil.refresh();
          });
      });
  }
}
