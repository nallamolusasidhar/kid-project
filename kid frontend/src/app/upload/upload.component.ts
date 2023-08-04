import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent {

  selectedFile!: File;
  resMessage: any="";
  imageName: any;
  name:string="";
  Class:string="";
  Section:string="";
  phoneno:string="";
  FatherName:string="";
  address:string="";


  constructor(private http:HttpClient){}
  ngOnInit(){
    this.selectedFile={} as any;
  }

  //Gets called when the user selects an image
  public onFileChanged(event:any) {
    this.selectedFile = event.target.files[0];
  }


  prdSubmitt(){
    
    const uploadImageData = new FormData();

    uploadImageData.append('dietFile', this.selectedFile, this.selectedFile.name);
    uploadImageData.append("name",this.name);
    uploadImageData.append("classname",this.Class);
    uploadImageData.append("section",this.Section);
    uploadImageData.append("fatherName",this.FatherName);
    uploadImageData.append("address",this.address);
    uploadImageData.append("phone",this.phoneno);


    
    

    let res =this.http.post("http://localhost:1234/stu/add",uploadImageData,
    {responseType:'text' as 'json'});
    res.subscribe(
      data=>{
        this.resMessage = data;
        console.log(data);
        this.name="";
        this.Class="";
        this.Section="";
        this.FatherName="";
        this.address="";
        this.phoneno="";
        
      }
    );

  }

}
