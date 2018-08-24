

$(document).ready(function(){	
	
		// menu left side
		$("#menu_tgl_btn").click(function(){
			$("#id_body").toggleClass('hide_menu');
			$(this).find("i").toggleClass("fa-indent fa-outdent");
		});
		// Menu
		$(".left_sub_menu").hide(); 
		$(".left_main_menu").click(function(e){
			e.preventDefault();
			$(this).parent().find("ul").slideToggle('fast'); 
			$(this).parent().find(".fa-angle-right").toggleClass('rotate90'); 
			$(this).parent().siblings().find("ul").slideUp('fast'); 
			$(this).parent().siblings().find(".fa-angle-right").removeClass('rotate90');
		});
	
});

// tooltip initialisation
$(function () {
	$('[data-toggle="tooltip"]').tooltip()
})


// multple select options

$(".dropdown dt a").on('click', function() {
	  $(".dropdown dd ul").slideToggle('fast');
	});

	$(".dropdown dd ul li a").on('click', function() {
	  $(".dropdown dd ul").hide();
	});

	function getSelectedValue(id) {
	  return $("#" + id).find("dt a span.value").html();
	}

	$(document).bind('click', function(e) {
	  var $clicked = $(e.target);
	  if (!$clicked.parents().hasClass("dropdown")) $(".dropdown dd ul").hide();
	});

	$('.mutliSelect input[type="checkbox"]').on('click', function() {

	  var title = $(this).closest('.mutliSelect').find('input[type="checkbox"]').val(),
	    title = $(this).val() + ",";

	  if ($(this).is(':checked')) {
	    var html = '<span title="' + title + '">' + title + '</span>';
	    $('.multiSel').append(html);
	    $(".hida").hide();
	  } else {
	    $('span[title="' + title + '"]').remove();
	    var ret = $(".hida");
	    $('.dropdown dt a').append(ret);

	  }
	});