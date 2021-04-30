
   grecaptcha.ready(function() {
       var sitekey = document.getElementById('sitekey').value;
       var secretkey = document.getElementById('secretkey').value;
       setCaptchaToken();
       function setCaptchaToken(){
       grecaptcha.execute(sitekey, {action: 'submit'}).then(function(token) {
           console.log(token);
           document.getElementById('g-recaptcha-response').value=token;
           if ($("#g-recaptcha-response").length) {
   var grecap = $("#g-recaptcha-response").val();
   var env = "";
   var sk = "6Ld_GN8UAAAAAEZFe1xSj9cK3MtAiQ0UAOb07yN0";
   $.ajax({
       type: 'POST',
   url: '/bin/recaptcha1',
   data: 'g-recaptcha-response='+ grecap +'&sk='+secretkey, 
   async: false, 
   dataType: 'json', 
   cache: false, 
   success: function(data) {
       console.log("recaptcha score " +data.score)
   if (data.success == false || data.score < 0.5) {

      $(":button").prop('disabled', true); 
   } else{
 $(":button").prop('disabled', false); 
          }
   }, 
   fail: function(textStatus, errorThrown) {  
   console.log(textStatus, errorThrown); 
   }});
   }
       });
   };
       setInterval(function () { setCaptchaToken(); }, 2 * 60 * 1000);
   });