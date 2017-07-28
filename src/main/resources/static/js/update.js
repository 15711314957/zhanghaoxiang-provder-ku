 $(document).ready(function(){
     $(":button[name='update']").click(function () {
         var u = $(this).val();
         alert(u);
        /* var url = "getUserById";
         var args={"id":u,"date":new date(),async:true};
         $.post(url,args,function(data) {
             $("#id").attr("value",data.id);
             $("#name").attr("value",data.name);
             $("#age").attr("value",data.age);
         },"json")*/
         $.ajax({
             type:"post",
             url:"getUserById",
             async:true,
             dataType:"json",
             data: {"id":u},
             success : function(data){
                 $("#id").attr("value",data.id);
                 $("#name").attr("value",data.name);
                 $("#age").attr("value",data.age);
             }

     })
     })
 });




