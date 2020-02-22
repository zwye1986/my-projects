/*!
 * Global Inc
 * Version: 1.0.0
 * Modify: By Wang Ping
 * Date: 07-03-2013 15:42:48 (GMT Time)
 */

(function($) {
    $.fn.switchCarousel = function(options) {
        var defaults = {
            move: 1,
            displayNum: 1,
            speed: 500,
            margin: 0,
            auto: true,
            autoInterval: 5000,
            autoDir: 'next',
            autoHover: true,
            nextText: 'next',
			prevText: 'prev',
            controls: true
        };
        var options = $.extend(defaults, options);
        return this.each(function() {
            var _this = $(this);
            var _li = _this.find('li');
            var _first = 0;
            var _fe = 0;
            var _last = options.displayNum - 1;
            var _le = options.displayNum - 1;
            var _isWorking = false;
            var _j = '';
            var _clicked = false;
            _li.css({
                'float': 'left',
                'listStyle': 'none',
                'marginRight': options.margin
            });
            var _ow = _li.outerWidth(true);
            var _wrapWidth = (_ow * options.displayNum) - options.margin;
            var _seg = _ow * options.move;
            _this.wrap('<div class="switch-container"></div>').width(999999);
            if (options.controls) {
                var _controls = '<a href="" class="icons prev">' + options.prevText + '</a><a href="" class="icons next">' + options.nextText + '</a>';
            };
            _this.parent('.switch-container').wrap('<div class="switch-wrap"></div>').css({
                'position': 'relative',
                'width': _wrapWidth,
                'overflow': 'hidden'
            }).before(_controls);
            var _w = _li.slice(0, options.displayNum).clone();
            var _lastAppended = (options.displayNum + options.move) - 1;
            _this.empty().append(_w);
            getP();
            getA();
            _this.css({
                'position': 'relative',
                'left': -(_seg)
            });
            _this.parent().siblings('.next').click(function() {
                slideNext();
                clearInterval(_j);
                _clicked = true;
                return false;
            });
            _this.parent().siblings('.prev').click(function() {
                slidePrev();
                clearInterval(_j);
                _clicked = true;
                return false;
            });
            if (options.auto) {
                startSlide();
                if (options.autoHover && _clicked != true) {
                    _this.find('li').live('mouseenter', function() {
                        if (!_clicked) {
                            clearInterval(_j);
                        }
                    });
                    _this.find('li').live('mouseleave', function() {
                        if (!_clicked) {
                            startSlide();
                        }
                    });
                }
            };
            function startSlide() {
                if (options.autoDir == 'next') {
                    _j = setInterval(function() {
                        slideNext()
                    }, options.autoInterval);
                } else {
                    _j = setInterval(function() {
                        slidePrev()
                    }, options.autoInterval);
                }
            };
            function slideNext() {
                if (!_isWorking) {
                    _isWorking = true;
                    setPos('next');
                    _this.animate({
                        left: '-=' + _seg
                    }, options.speed, function() {
                        _this.find('li').slice(0, options.move).remove();
                        _this.css('left', -(_seg));
                        getA();
                        _isWorking = false;
                    });
                }
            };
            function slidePrev() {
                if (!_isWorking) {
                    _isWorking = true;
                    setPos('prev');
                    _this.animate({
                        left: '+=' + _seg
                    }, options.speed, function() {
                        _this.find('li').slice( - options.move).remove();
                        _this.css('left', -(_seg));
                        getP();
                        _isWorking = false;
                    });
                }
            };
            function getA() {
                var str = new Array();
                var lix = _li.clone();
                _le = _last;
                for (i = 0; i < options.move; i++) {
                    _le++
                    if (lix[_le] != undefined) {
                        str[i] = $(lix[_le]);
                    } else {
                        _le = 0;
                        str[i] = $(lix[_le]);
                    }
                };
                $.each(str,function(index) {
                    _this.append(str[index][0]);
                });
            };
            function getP() {
                var str = new Array();
                var lix = _li.clone();
                _fe = _first;
                for (i = 0; i < options.move; i++) {
                    _fe--
                    if (lix[_fe] != undefined) {
                        str[i] = $(lix[_fe]);
                    } else {
                        _fe = _li.length - 1;
                        str[i] = $(lix[_fe]);
                    }
                };
                $.each(str,function(index) {
                    _this.prepend(str[index][0]);
                });
            };
            function setPos(dir) {
                if (dir == 'next') {
                    _first += options.move;
                    if (_first >= _li.length) {
                        _first = _first % _li.length;
                    };
                    _last += options.move;
                    if (_last >= _li.length) {
                        _last = _last % _li.length;
                    };
                } else if (dir == 'prev') {
                    _first -= options.move;
                    if (_first < 0) {
                        _first = _li.length + _first;
                    };
                    _last -= options.move;
                    if (_last < 0) {
                        _last = _li.length + _last;
                    };
                };
            };
        });
    };
})(jQuery);