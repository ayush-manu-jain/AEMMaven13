
$(document).ready(function() {


// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

var cancelbtn = document.getElementById("cancel");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
  modal.style.display = "block";
}

cancelbtn.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

    $('body').hide().fadeIn(5000);

$('#deleteemail').click(function() {
    var failure = function(err) {
             alert("Unable to retrive data "+err);
   };

    var email= $('#Email').val();
    var action= $('#deleteemail').val();
    $.ajax({
           type: 'POST',    
         url:'/bin/abc1',
         data:'email='+ email +'&action='+ action,
         success: function(msg){
            $("#modalcontentbody").text(msg);
         }
     });
  });

    $('#myBtn').click(function() {
    var failure = function(err) {
             alert("Unable to retrive data "+err);
   };

    var email= $('#Email').val();
	var action= $('#myBtn').val();
    $.ajax({
           type: 'POST',    
         url:'/bin/abc1',
         data:'email='+ email +'&action='+ action,
         success: function(msg){
        if(msg=="fail"){
        document.getElementById("hidemodalcontent").style.display = "block";
        $("#hidemodalcontent").text("User not found");
        document.getElementById("modalcontentbody").style.display = "none";
    }else{
           var json = jQuery.parseJSON(msg);
        	var userid = json.userid;
        	var firstName = json.firstname;
            var lastName = json.lastname;
        document.getElementById("modalcontentbody").style.display = "block";
        $("#modalcontent").text("User details :::: User Id : " +userid +" First Name : "  +firstName +" Last Name : " +lastName);
        document.getElementById("hidemodalcontent").style.display = "none";
           }

         }
     });
  });

});
