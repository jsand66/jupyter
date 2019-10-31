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
  container_name: any;
  container_id: any;
  height: any;

  port: any;
  loading = false;
  iframe_url: any;
  binaryString: any;
  filename: any;
  constructor(private http: Http, private sanitizer: DomSanitizer) { }

  ngOnInit() {
    this.check_jupyter();
    this.height = window.innerHeight - 120;
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
      if(this.binaryString!=undefined)
      {
        this.loading=false;
        var requestData = { 'data': btoa(this.binaryString), 'filename': this.filename, 'container_id': this.container_id };
        this.upload_service(requestData).then((data: any) => {
          alert("File Uploaded Successfully")
          this.loading=false;
        })
      }
      else
      {
        alert("please select file and upload")
      }
  }
  stopJupyter(){
    this.loading=true;
    var requestData = { 'container_id': this.container_id};
    this.stop_jupyter_service(requestData).then((data) => {
      if(data!=undefined)
      {
        this.iframe_url=undefined;
        this.port=undefined;
        this.container_id=undefined;
        this.loading=false;
        alert("Jupyter Stopped")
      }
      else{
        alert("Service Error ")
        this.loading = false;
      }
      
    })
  }
  check_jupyter(){
    this.loading=true;
    this.check_jupyter_service().then((data: any) => {
      if(data.status=='yes')
      {
        this.container_id=data.container_id;
        this.port=data.port;
        this.container_name=data.container_name;
        let url = environment.ip + this.port + "/lab"
        this.iframe_url = this.sanitizer.bypassSecurityTrustResourceUrl(url)
        this.loading = false;
      }
      else
      {
        this.loading = false;
        alert("Please Start Jupyter")
      }
     
    })

  }
  start_jupyter() {
    this.loading = true;
   
      this.start_jupyter_service().then((data: any) => {
       
        if (data.port != undefined && data.port.length>0) {
        setTimeout(()=> {
          this.port = data.port;
          this.container_id=data.container_id;
          let url = environment.ip + this.port + "/lab"
          this.iframe_url = this.sanitizer.bypassSecurityTrustResourceUrl(url)
          this.loading = false;
        }, 12000);
        }
        else{
          alert("Jupyter Image is not Available")
          this.loading = false;
        }
      })
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
            this.loading = false;
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
            this.loading = false;
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
            this.loading = false;
            // ////this.congnitoUtil.refresh();
          });
      });
  }
  check_jupyter_service() {
   
    return new Promise(
      (resolve, reject) => {
        return this.http.post(environment.ip + environment.apiendpoint + 'checkContainer','')
          .subscribe(res => {
            const data: any = res.json()
            resolve(data);
          },
          (err) => {
            reject(err);
            this.loading = false;
            // ////this.congnitoUtil.refresh();
          });
      });
}
}
