jQuery(function($) {

    $('ul.comics li').ahover({toggleEffect: 'width'});
    $('ul.cards li').ahover({moveSpeed: 100, hoverEffect: function() {
        $(this)
            .css({opacity: 0.99})
            .animate({opacity: 0.5}, 750)
            .animate({opacity: 0.99}, 750)
            .dequeue();
        $(this).queue(arguments.callee);
    }});
    $('ul.letters li').ahover({toggleEffect: 'height', moveSpeed: 75, toggleSpeed: 250});
});
