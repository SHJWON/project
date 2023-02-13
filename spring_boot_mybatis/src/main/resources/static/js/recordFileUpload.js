/**
 * 
 */
 $(document).ready(function(){
 //1
	 const recordBtn= document.getElementById("recordBtn");
	 const stopBtn= document.getElementById("stopBtn");
	 
	 if(navigator.mediaDevices){
	   var constraints={
	   audio:true
	 }
	 let chunks=[];//녹음데이터 저장하기 위한 변수
	 
	 navigator.mediaDevices.getUserMedia(constraints)
	   .then(stream=>{
	      const mediaRecorder= new MediaRecorder(stream);
	 
	 //버튼 ㄴ눌었을때
	 recordBtn.onclick=()=>{
	 mediaRecorder.start();
	 recordBtn.style.background="red"
	 recordBtn.style.color="black";
	 
	 }//스탑버튼 클릭했을때
	  stopBtn.onclick=()=>{
	 mediaRecorder.stop();
	 recordBtn.style.background=""
	 recordBtn.style.color="";
	 
	 }
	 //chunks
	 mediaRecorder.onstop=e=>{
	 const blob= new Blob(chunks,{
	 'type':'audio/mp3 codecs=opus'
	 
	 });
	 chunks=[];
	 //오디오 객체 생성
	 const audio=document.createElement('audio');
	 
	 audio.src= URL.createObjectURL(blob);
	 
	 var num= new Date();
	 
	 const clipName= num.getTime()+"_voiceMsg";
	 
	 fileUploadFn(blob,clipName);
	 }
	 mediaRecorder.ondataavailable=e=>{
	 chunks.push(e.data);
     }
     }).catch(err=>{
      console.log("오류발생"+err);
    });
  
}
 //////////////////
 //2)
 function fileUploadFn(blob,clipName){
 
    var formData= new FormData();
    formData.append('uploadFile',blob, clipName+".mp3");
    //서버에 전달
     $.ajax({
 			type:"post",
 			url:"allFileUpload",
 			enctype:"multipart/form-data",
 			processData:false,
 			contentType:false,
 			data: formData,
 			success:function(result){
 				if(result == "success"){
 					$('#audioBox').html('<audio src="/audio/' + clipName + '.mp3"controls>');
 					
 				}
 			},
 			error:function(){
 				alert("실패");
 			},
 			complete:function(){
 				//alert("작업 완료");
 			}
 		}); // ajax 종료 	

 }
 });