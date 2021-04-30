$(document).ready(function() {

    $('body').hide().fadeIn(5000);
        
$('#submit').click(function() {
    var failure = function(err) {
             alert("Unable to retrive data "+err);
   };
 
    //Get the user-defined values that represent claim data to persist in the Adobe CQ JCR
    var myFirst= $('#FirstName').val(); 
    var myLast= $('#LastName').val();
    alert("firstName: "+myFirst +" lastName: " +myLast)
    //Use a Sling Servlet Post to persist the claim data into the Adobe JCR under content/claim
    $.ajax({
           type: 'POST',    
         url:'/bin/abc',
         data:'firstName='+ myFirst+'&lastName='+ myLast,
         success: function(msg){
        var json = jQuery.parseJSON(msg); 
            var lastName = json.lastname;
            var firstName = json.firstname;
            alert("CCCCCCCCCCCCCCC " +msg);
         }
     });
  });
    
});