

// sideBarTreeaccordian.js
// Requires jquery.js and velocity.js (optional but recommended).
// Copy the below function, add to your JS, and simply add a list <ul class=sideBarTree> ... </ul>
;(function ($, window, document, undefined) {
  
  // Only apply if sideBarTree list exists
  if($('ul.sideBarTree').length) { 
  
    
  // Settings
  var collapsed = true; // Start with collapsed menu (only level 1 items visible)
  var close_same_level = true; // Close elements on same level when opening new node.
  var duration = 400; // Animation duration should be tweaked according to easing.
  var listAnim = true; // Animate separate list items on open/close element (velocity.js only).
  var easing = 'easeOutQuart'; // Velocity.js only, defaults to 'swing' with jquery animation.
    
  
  // Set initial styles 
  $('.sideBarTree ul').css({'overflow':'hidden', 'height': (collapsed) ? 0 : 'auto', 'display': (collapsed) ? 'none' : 'block' });
  
  // Get node elements, and add classes for styling
  var node = $('.sideBarTree li:has(ul)');  
  node.each(function(index, val) {
    $(this).children(':first-child').css('cursor', 'pointer')
    $(this).addClass('sideBarTree-node sideBarTree-' + ((collapsed) ? 'closed' : 'open'));
    $(this).children('ul').addClass('sideBarTree-level-' + ($(this).parentsUntil($('ul.sideBarTree'), 'ul').length + 1));
  });
  
  // Set sideBarTree-active class on list items for last opened element
  $('.sideBarTree li > *:first-child').on('click.sideBarTree-active', function(e){
    if($(this).parent().hasClass('sideBarTree-closed')) {
      $('.sideBarTree-active').not($(this).parent()).removeClass('sideBarTree-active');
      $(this).parent().addClass('sideBarTree-active');
    } else if($(this).parent().hasClass('sideBarTree-open')){
      $(this).parent().removeClass('sideBarTree-active'); 
    } else {
      $('.sideBarTree-active').not($(this).parent()).removeClass('sideBarTree-active');
      $(this).parent().toggleClass('sideBarTree-active'); 
    }
  });

  // Set node click elements, preferably <a> but node links can be <span> also
  node.children(':first-child').on('click.sideBarTree', function(e){
    
    // element vars
    var el = $(this).parent().children('ul').first();
    var isOpen = $(this).parent().hasClass('sideBarTree-open');
    
    // close other elements on same level if opening 
    if((close_same_level || $('.csl').hasClass('active')) && !isOpen) {
      var close_items = $(this).closest('ul').children('.sideBarTree-open').not($(this).parent()).children('ul');
      
      // Velocity.js
      if($.Velocity) {
        close_items.velocity({
          height: 0
        }, {
          duration: duration,
          easing: easing,
          display: 'none',
          delay: 100,
          complete: function(){
            setNodeClass($(this).parent(), true)
          }
        });
        
      // jQuery fallback
      } else {
        close_items.delay(100).slideToggle(duration, function(){
          setNodeClass($(this).parent(), true);
        });
      }
    }
    
    // force auto height of element so actual height can be extracted
    el.css({'height': 'auto'}); 
    
    // listAnim: animate child elements when opening
    if(!isOpen && $.Velocity && listAnim) el.find(' > li, li.sideBarTree-open > ul > li').css({'opacity':0}).velocity('stop').velocity('list');
    
    // Velocity.js animate element
    if($.Velocity) {
      el.velocity('stop').velocity({
        //translateZ: 0, // optional hardware-acceleration is automatic on mobile
        height: isOpen ? [0, el.outerHeight()] : [el.outerHeight(), 0]
      },{
        queue: false,
        duration: duration,
        easing: easing,
        display: isOpen ? 'none' : 'block',
        begin: setNodeClass($(this).parent(), isOpen),
        complete: function(){
          if(!isOpen) $(this).css('height', 'auto');
        }
      });
    
    // jQuery fallback animate element
    } else {
      setNodeClass($(this).parent(), isOpen);
      el.slideToggle(duration);
    }
    
    // We can't have nodes as links unfortunately
    e.preventDefault();
  });
  
  // Function for updating node class
  function setNodeClass(el, isOpen) {
    if(isOpen) {
      el.removeClass('sideBarTree-open').addClass('sideBarTree-closed');
    } else {
      el.removeClass('sideBarTree-closed').addClass('sideBarTree-open');
    }
  }
  
  // List animation sequence
  if($.Velocity && listAnim) {
    $.Velocity.Sequences.list = function (element, options, index, size) {
      $.Velocity.animate(element, { 
        opacity: [1,0],
        translateY: [0, -(index+1)]
      }, {
        delay: index*(duration/size/2),
        duration: duration,
        easing: easing
      });
    };
  }
    
    // Fade in sideBarTree after classes are added.
    // Useful if you have set collapsed = true or applied styles that change the structure so the menu doesn't jump between states after the function executes.
    if($('.sideBarTree').css('opacity') == 0) {
      if($.Velocity) {
        $('.sideBarTree').css('opacity', 1).children().css('opacity', 0).velocity('list');
      } else {
        $('.sideBarTree').show(200);
      }
    }
  }
}(jQuery, this, this.document));