import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CsvuploadComponent}  from'./csvupload/csvupload.component'
export const routes: Routes = [
    {
        path: '',
        component: CsvuploadComponent,
    },
    {
        path: 'csv',
        component: CsvuploadComponent,
    },
]
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }