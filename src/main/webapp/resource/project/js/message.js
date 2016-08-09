function messageShowModal(options) {
	if (options.msg != null) {
		options.modal.find(".modal-body").html(options.msg);
	}
	if (options.title != null) {
		options.modal.find(".modal-title").html(options.title);
	}
	options.modal.modal("show").css(
	    {
		    "top" : function() {
			    return ($(window).height() - options.modal.find(".modal-content").height()) * (1 - 0.618);
		    }
	    });
	options.modal.find(".ok").unbind();
	options.modal.find(".ok").click(function() {
		if (options.callBack != null) {
			options.callBack();
		}
		if (options.hide==null||options.hide) {
			options.modal.modal("hide");
		}
	});
}

jQuery.extend({
  message_alert : function(options) {
	  var modal = $(".alert");
	  if(options==null){
	  	options={};
	  }
	  options.modal=modal;
	  messageShowModal(options);
  },
  message_confirm : function(options) {
	  var modal = $(".confirm");
	  if(options==null){
	  	options={};
	  }
	  options.modal=modal;
	  messageShowModal(options);
  }
});

jQuery.fn.extend({
	message : function(options) {
		var modal = $(this);
		if(options==null){
	  	options={};
	  }
		options.modal=modal;
		messageShowModal(options);
	}
});