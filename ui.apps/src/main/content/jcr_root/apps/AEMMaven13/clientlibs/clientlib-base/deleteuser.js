    $(document).on("click", ".cq-dialog-submit", function(e) {
var $formminmax = $(this).closest("form.foundation-form");
    var email = $formminmax.find("[name='./email']").val();
$.ajax({
           type: 'POST',    
         url:'/bin/abc1',
         data:'email='+ email,
         success: function(msg){
            alert(msg);
         }
     });


  });