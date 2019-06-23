(function(window) {

    if(window.PrimeFaces) {
        window.PrimeFaces.debug("PrimeFaces already loaded, ignoring duplicate execution.");
        return;
    }

    var PrimeFaces = {

        escapeClientId : function(id) {
            return "#" + id.replace(/:/g,"\\:");
        },

        cleanWatermarks : function(){
            $.watermark.hideAll();
        },

        showWatermarks : function(){
            $.watermark.showAll();
        },

        getWidgetById : function(id) {

            for (var widgetVar in PrimeFaces.widgets) {
                var widget = PrimeFaces.widgets[widgetVar];
                if (widget && widget.id === id) {
                    return widget;
                }
            }

            return null;
        },

        addSubmitParam : function(parent, params) {
            var form = $(this.escapeClientId(parent));

            for(var key in params) {
                form.append("<input type=\"hidden\" name=\"" + key + "\" value=\"" + params[key] + "\" class=\"ui-submit-param\"/>");
            }

            return this;
        },

        /**
         * Submits a form and clears ui-submit-param after that to prevent dom caching issues
         */
        submit : function(formId, target) {
            var form = $(this.escapeClientId(formId));
            var prevTarget;

            if (target) {
                prevTarget = form.attr('target');
                form.attr('target', target);
            }

            form.submit();
            form.children('input.ui-submit-param').remove();

            if (target) {
                if (prevTarget !== undefined) {
                    form.attr('target', prevTarget);
                } else {
                    form.removeAttr('target');
                }
            }
        },

        onPost : function() {
            this.nonAjaxPosted = true;
            this.abortXHRs();
        },

        abortXHRs : function() {
            PrimeFaces.ajax.Queue.abortAll();
        },

        attachBehaviors : function(element, behaviors) {
            $.each(behaviors, function(event, fn) {
                element.bind(event, function(e) {
                    fn.call(element, e);
                });
            });
        },

        getCookie : function(name) {
            return $.cookie(name);
        },

        setCookie : function(name, value, cfg) {
            $.cookie(name, value, cfg);
        },

        deleteCookie: function(name, cfg) {
            $.removeCookie(name, cfg);
        },

        cookiesEnabled: function() {
            var cookieEnabled = (navigator.cookieEnabled) ? true : false;

            if(typeof navigator.cookieEnabled === 'undefined' && !cookieEnabled) {
                document.cookie="testcookie";
                cookieEnabled = (document.cookie.indexOf("testcookie") !== -1) ? true : false;
            }

            return (cookieEnabled);
        },

        skinInput : function(input) {
            input.hover(
                function() {
                    $(this).addClass('ui-state-hover');
                },
                function() {
                    $(this).removeClass('ui-state-hover');
                }
            ).focus(function() {
                $(this).addClass('ui-state-focus');
            }).blur(function() {
                $(this).removeClass('ui-state-focus');
            });

            //aria
            input.attr('role', 'textbox')
                    .attr('aria-disabled', input.is(':disabled'))
                    .attr('aria-readonly', input.prop('readonly'));

            if(input.is('textarea')) {
                input.attr('aria-multiline', true);
            }

            return this;
        },

        skinButton : function(button) {
            button.mouseover(function(){
                var el = $(this);
                if(!button.prop('disabled')) {
                    el.addClass('ui-state-hover');
                }
            }).mouseout(function() {
                $(this).removeClass('ui-state-active ui-state-hover');
            }).mousedown(function() {
                var el = $(this);
                if(!button.prop('disabled')) {
                    el.addClass('ui-state-active').removeClass('ui-state-hover');
                }
            }).mouseup(function() {
                $(this).removeClass('ui-state-active').addClass('ui-state-hover');
            }).focus(function() {
                $(this).addClass('ui-state-focus');
            }).blur(function() {
                $(this).removeClass('ui-state-focus ui-state-active');
            }).keydown(function(e) {
                if(e.which === $.ui.keyCode.SPACE || e.which === $.ui.keyCode.ENTER || e.which === $.ui.keyCode.NUMPAD_ENTER) {
                    $(this).addClass('ui-state-active');
                }
            }).keyup(function() {
                $(this).removeClass('ui-state-active');
            });

            //aria
            var role = button.attr('role');
            if(!role) {
                button.attr('role', 'button');
            }
            button.attr('aria-disabled', button.prop('disabled'));

            return this;
        },

        skinSelect : function(select) {
            select.mouseover(function() {
                var el = $(this);
                if(!el.hasClass('ui-state-focus'))
                    el.addClass('ui-state-hover');
            }).mouseout(function() {
                $(this).removeClass('ui-state-hover');
            }).focus(function() {
                $(this).addClass('ui-state-focus').removeClass('ui-state-hover');
            }).blur(function() {
                $(this).removeClass('ui-state-focus ui-state-hover');
            });

            return this;
        },

        info: function(log) {
            if(this.logger) {
                this.logger.info(log);
            }
        },

        debug: function(log) {
            if(this.logger) {
                this.logger.debug(log);
            }
        },

        warn: function(log) {
            if(this.logger) {
                this.logger.warn(log);
            }

            if (PrimeFaces.isDevelopmentProjectStage() && window.console) {
                console.log(log);
            }
        },

        error: function(log) {
            if(this.logger) {
                this.logger.error(log);
            }

            if (PrimeFaces.isDevelopmentProjectStage() && window.console) {
                console.error(log);
            }
        },

        isDevelopmentProjectStage: function() {
            return PrimeFaces.settings.projectStage === 'Development';
        },

        setCaretToEnd: function(element) {
            if(element) {
                element.focus();
                var length = element.value.length;

                if(length > 0) {
                    if(element.setSelectionRange) {
                        element.setSelectionRange(0, length);
                    }
                    else if (element.createTextRange) {
                      var range = element.createTextRange();
                      range.collapse(true);
                      range.moveEnd('character', 1);
                      range.moveStart('character', 1);
                      range.select();
                    }
                }
            }
        },

        changeTheme: function(newTheme) {
            if(newTheme && newTheme !== '') {
                var themeLink = $('link[href*="' + PrimeFaces.RESOURCE_IDENTIFIER + '/theme.css"]');
                // portlet
                if (themeLink.length === 0) {
                    themeLink = $('link[href*="' + PrimeFaces.RESOURCE_IDENTIFIER + '=theme.css"]');
                }

                var themeURL = themeLink.attr('href'),
                    plainURL = themeURL.split('&')[0],
                    oldTheme = plainURL.split('ln=')[1],
                    newThemeURL = themeURL.replace(oldTheme, 'primefaces-' + newTheme);

                themeLink.attr('href', newThemeURL);
            }
        },

        escapeRegExp: function(text) {
            return this.escapeHTML(text.replace(/([.?*+^$[\]\\(){}|-])/g, "\\$1"));
        },

        escapeHTML: function(value) {
            return value.replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;');
        },

        clearSelection: function() {
            if(window.getSelection) {
                if(window.getSelection().empty) {
                    window.getSelection().empty();
                } else if(window.getSelection().removeAllRanges && window.getSelection().rangeCount > 0 && window.getSelection().getRangeAt(0).getClientRects().length > 0) {
                    window.getSelection().removeAllRanges();
                }
            }
            else if(document.selection && document.selection.empty) {
                try {
                    document.selection.empty();
                } catch(error) {
                    //ignore IE bug
                }
            }
        },

        getSelection: function() {
            var text = '';
            if (window.getSelection) {
                text = window.getSelection();
            } else if (document.getSelection) {
                text = document.getSelection();
            } else if (document.selection) {
                text = document.selection.createRange().text;
            }

            return text;
        },

        hasSelection: function() {
            return this.getSelection().length > 0;
        },

        cw : function(widgetName, widgetVar, cfg) {
            this.createWidget(widgetName, widgetVar, cfg);
        },

        createWidget : function(widgetName, widgetVar, cfg) {
            cfg.widgetVar = widgetVar;

            if(this.widget[widgetName]) {
                var widget = this.widgets[widgetVar];

                //ajax update
                if(widget && (widget.constructor === this.widget[widgetName])) {
                    widget.refresh(cfg);
                }
                //page init
                else {
                    this.widgets[widgetVar] = new this.widget[widgetName](cfg);
                    if(this.settings.legacyWidgetNamespace) {
                        window[widgetVar] = this.widgets[widgetVar];
                    }
                }
            }
            // widget script not loaded
            else {
                // should be loaded by our dynamic resource handling, log a error
                PrimeFaces.error("Widget not available: " + widgetName);
            }
        },

        /**
         * Builds a resource URL for given parameters.
         *
         * @param {string} name The name of the resource. For example: primefaces.js
         * @param {string} library The library of the resource. For example: primefaces
         * @param {string} version The version of the library. For example: 5.1
         * @returns {string} The resource URL.
         */
        getFacesResource : function(name, library, version) {

            // just get sure - name shoudln't start with a slash
            if (name.indexOf('/') === 0)
            {
                name = name.substring(1, name.length);
            }

            var scriptURI = $('script[src*="/' + PrimeFaces.RESOURCE_IDENTIFIER + '/core.js"]').attr('src');
            // portlet
            if (!scriptURI) {
                scriptURI = $('script[src*="' + PrimeFaces.RESOURCE_IDENTIFIER + '=core.js"]').attr('src');
            }

            scriptURI = scriptURI.replace('core.js', name);
            scriptURI = scriptURI.replace('ln=primefaces', 'ln=' + library);

            if (version) {
                var extractedVersion = new RegExp('[?&]v=([^&]*)').exec(scriptURI)[1];
                scriptURI = scriptURI.replace('v=' + extractedVersion, 'v=' + version);
            }

            var prefix = window.location.protocol + '//' + window.location.host;
            return scriptURI.indexOf(prefix) >= 0 ? scriptURI : prefix + scriptURI;
        },

        inArray: function(arr, item) {
            for(var i = 0; i < arr.length; i++) {
                if(arr[i] === item) {
                    return true;
                }
            }

            return false;
        },

        isNumber: function(value) {
            return typeof value === 'number' && isFinite(value);
        },

        getScript: function(url, callback) {
            $.ajax({
                type: "GET",
                url: url,
                success: callback,
                dataType: "script",
                cache: true,
                async: true
            });
        },

        focus: function(id, context) {
            var selector = ':not(:submit):not(:button):input:visible:enabled[name]';

            setTimeout(function() {
                if(id) {
                    var jq = $(PrimeFaces.escapeClientId(id));

                    if(jq.is(selector)) {
                        jq.focus();
                    }
                    else {
                        var firstElement = jq.find(selector).eq(0);
                        PrimeFaces.focusElement(firstElement);
                    }
                }
                else if(context) {
                    var firstElement = $(PrimeFaces.escapeClientId(context)).find(selector).eq(0);
                    PrimeFaces.focusElement(firstElement);
                }
                else {
                    var elements = $(selector),
                    firstElement = elements.eq(0);
                    if(firstElement.is(':radio')) {
                        var checkedRadio = $(':radio[name="' + firstElement.attr('name') + '"]').filter(':checked');
                        if(checkedRadio.length)
                            PrimeFaces.focusElement(checkedRadio);
                        else
                            PrimeFaces.focusElement(firstElement);
                    }
                    else {
                        firstElement.focus();
                    }
                }
            }, 50);

            // remember that a custom focus has been rendered
            // this avoids to retain the last focus after ajax update
            PrimeFaces.customFocus = true;
        },

        focusElement: function(el) {
            if(el.is(':radio') && el.hasClass('ui-helper-hidden-accessible')) {
                el.parent().focus();
            }
            else {
                el.focus();
            }
        },

        monitorDownload: function(start, complete, monitorKey) {
            if(this.cookiesEnabled()) {
                if(start) {
                    start();
                }

                var cookieName = monitorKey ? 'primefaces.download_' + monitorKey : 'primefaces.download';
                window.downloadMonitor = setInterval(function() {
                    var downloadComplete = PrimeFaces.getCookie(cookieName);

                    if(downloadComplete === 'true') {
                        if(complete) {
                            complete();
                        }
                        clearInterval(window.downloadMonitor);
                        PrimeFaces.setCookie(cookieName, null);
                    }
                }, 1000);
            }
        },

        /**
         *  Scrolls to a component with given client id
         */
        scrollTo: function(id) {
            var offset = $(PrimeFaces.escapeClientId(id)).offset();

            $('html,body').animate({
                scrollTop:offset.top
                ,
                scrollLeft:offset.left
            },{
                easing: 'easeInCirc'
            },1000);

        },

        /**
         *  Aligns container scrollbar to keep item in container viewport, algorithm copied from jquery-ui menu widget
         */
        scrollInView: function(container, item) {
            if(item.length === 0) {
                return;
            }

            var borderTop = parseFloat(container.css('borderTopWidth')) || 0,
            paddingTop = parseFloat(container.css('paddingTop')) || 0,
            offset = item.offset().top - container.offset().top - borderTop - paddingTop,
            scroll = container.scrollTop(),
            elementHeight = container.height(),
            itemHeight = item.outerHeight(true);

            if(offset < 0) {
                container.scrollTop(scroll + offset);
            }
            else if((offset + itemHeight) > elementHeight) {
                container.scrollTop(scroll + offset - elementHeight + itemHeight);
            }
        },

        calculateScrollbarWidth: function() {
            if(!this.scrollbarWidth) {
                if(PrimeFaces.env.browser.msie) {
                    var $textarea1 = $('<textarea cols="10" rows="2"></textarea>')
                            .css({ position: 'absolute', top: -1000, left: -1000 }).appendTo('body'),
                        $textarea2 = $('<textarea cols="10" rows="2" style="overflow: hidden;"></textarea>')
                            .css({ position: 'absolute', top: -1000, left: -1000 }).appendTo('body');
                    this.scrollbarWidth = $textarea1.width() - $textarea2.width();
                    $textarea1.add($textarea2).remove();
                }
                else {
                    var $div = $('<div />')
                        .css({ width: 100, height: 100, overflow: 'auto', position: 'absolute', top: -1000, left: -1000 })
                        .prependTo('body').append('<div />').find('div')
                            .css({ width: '100%', height: 200 });
                    this.scrollbarWidth = 100 - $div.width();
                    $div.parent().remove();
                }
            }

            return this.scrollbarWidth;
        },

        bcn: function(element, event, functions) {
            if(functions) {
                for(var i = 0; i < functions.length; i++) {
                    var retVal = functions[i].call(element, event);
                    if(retVal === false) {
                        if(event.preventDefault) {
                            event.preventDefault();
                        }
                        else {
                            event.returnValue = false;
                        }

                        break;
                    }
                }
            }
        },

        bcnu: function(ext, event, fns) {
            if(fns) {
                for(var i = 0; i < fns.length; i++) {
                    var retVal = fns[i].call(this, ext, event);
                    if(retVal === false) {
                        break;
                    }
                }
            }
        },

    	/**
    	 * moved to core.dialog.js
    	 */
        openDialog: function(cfg) {
        	PrimeFaces.dialog.DialogHandler.openDialog(cfg);
        },
        closeDialog: function(cfg) {
        	PrimeFaces.dialog.DialogHandler.closeDialog(cfg);
        },
        showMessageInDialog: function(msg) {
        	PrimeFaces.dialog.DialogHandler.showMessageInDialog(msg);
        },
        confirm: function(msg) {
        	PrimeFaces.dialog.DialogHandler.confirm(msg);
        },

        deferredRenders: [],

        addDeferredRender: function(widgetId, containerId, fn) {
            this.deferredRenders.push({widget: widgetId, container: containerId, callback: fn});
        },

        removeDeferredRenders: function(widgetId) {
            for(var i = (this.deferredRenders.length - 1); i >= 0; i--) {
                var deferredRender = this.deferredRenders[i];

                if(deferredRender.widget === widgetId) {
                    this.deferredRenders.splice(i, 1);
                }
            }
        },

        invokeDeferredRenders: function(containerId) {
            var widgetsToRemove = [];
            for(var i = 0; i < this.deferredRenders.length; i++) {
                var deferredRender = this.deferredRenders[i];

                if(deferredRender.container === containerId) {
                    var rendered = deferredRender.callback.call();
                    if(rendered) {
                        widgetsToRemove.push(deferredRender.widget);
                    }
                }
            }

            for(var j = 0; j < widgetsToRemove.length; j++) {
                this.removeDeferredRenders(widgetsToRemove[j]);
            }
        },

        getLocaleSettings: function() {
            if(!this.localeSettings) {
                var localeKey = PrimeFaces.settings.locale;
                this.localeSettings = PrimeFaces.locales[localeKey];

                if(!this.localeSettings) {
                    if(localeKey) {
                       this.localeSettings = PrimeFaces.locales[localeKey.split('_')[0]];
                    }

                    if(!this.localeSettings)
                        this.localeSettings = PrimeFaces.locales['en_US'];
                }
            }

            return this.localeSettings;
        },

        getAriaLabel: function(key) {
            var ariaLocaleSettings = this.getLocaleSettings()['aria'];
            return (ariaLocaleSettings&&ariaLocaleSettings[key]) ? ariaLocaleSettings[key] : PrimeFaces.locales['en_US']['aria'][key];
        },

        zindex : 1000,

        customFocus : false,

        detachedWidgets : [],

        PARTIAL_REQUEST_PARAM : "javax.faces.partial.ajax",

        PARTIAL_UPDATE_PARAM : "javax.faces.partial.render",

        PARTIAL_PROCESS_PARAM : "javax.faces.partial.execute",

        PARTIAL_SOURCE_PARAM : "javax.faces.source",

        BEHAVIOR_EVENT_PARAM : "javax.faces.behavior.event",

        PARTIAL_EVENT_PARAM : "javax.faces.partial.event",

        RESET_VALUES_PARAM : "primefaces.resetvalues",

        IGNORE_AUTO_UPDATE_PARAM : "primefaces.ignoreautoupdate",

        SKIP_CHILDREN_PARAM : "primefaces.skipchildren",

        VIEW_STATE : "javax.faces.ViewState",

        CLIENT_WINDOW : "javax.faces.ClientWindow",

        VIEW_ROOT : "javax.faces.ViewRoot",

        CLIENT_ID_DATA : "primefaces.clientid",

        RESOURCE_IDENTIFIER: 'javax.faces.resource',

        VERSION: '6.2'
    };

    /**
     * PrimeFaces Namespaces
     */
    PrimeFaces.settings = {};
    PrimeFaces.util = {};
    PrimeFaces.widgets = {};

    /**
     * Locales
     */
    PrimeFaces.locales = {

        'en_US': {
            closeText: 'Close',
            prevText: 'Previous',
            nextText: 'Next',
            monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December' ],
            monthNamesShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ],
            dayNames: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
            dayNamesShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
            dayNamesMin: ['S', 'M', 'T', 'W ', 'T', 'F ', 'S'],
            weekHeader: 'Week',
            firstDay: 0,
            isRTL: false,
            showMonthAfterYear: false,
            yearSuffix:'',
            timeOnlyTitle: 'Only Time',
            timeText: 'Time',
            hourText: 'Hour',
            minuteText: 'Minute',
            secondText: 'Second',
            currentText: 'Current Date',
            ampm: false,
            month: 'Month',
            week: 'Week',
            day: 'Day',
            allDayText: 'All Day',
            aria: {
                'paginator.PAGE': 'Page {0}',
                'calendar.BUTTON': 'Show Calendar',
                'datatable.sort.ASC': 'activate to sort column ascending',
                'datatable.sort.DESC': 'activate to sort column descending',
                'columntoggler.CLOSE': 'Close'
            }
        }

    };

    PrimeFaces.locales['en'] = PrimeFaces.locales['en_US'];

    PF = function(widgetVar) {
    	var widgetInstance = PrimeFaces.widgets[widgetVar];

    	if (!widgetInstance) {
	        PrimeFaces.error("Widget for var '" + widgetVar + "' not available!");
    	}

        return widgetInstance;
    };

    //expose globally
    window.PrimeFaces = PrimeFaces;

})(window);

if (!PrimeFaces.env) {

    PrimeFaces.env = {

        mobile : false,
        touch : false,
        ios: false,
        browser : null,

        init : function() {
            this.mobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(window.navigator.userAgent);
            this.touch = 'ontouchstart' in window || window.navigator.msMaxTouchPoints || PrimeFaces.env.mobile;
            this.ios = /iPhone|iPad|iPod/i.test(window.navigator.userAgent);

            this.resolveUserAgent();
        },
        
        //adapted from jquery browser plugin
        resolveUserAgent: function() { 
            if($.browser) {
                this.browser = $.browser;
            }
            else {
                var matched, browser;

                jQuery.uaMatch = function( ua ) {
                    ua = ua.toLowerCase();

                    var match = /(opr)[\/]([\w.]+)/.exec( ua ) ||
                        /(chrome)[ \/]([\w.]+)/.exec( ua ) ||
                        /(version)[ \/]([\w.]+).*(safari)[ \/]([\w.]+)/.exec( ua ) ||
                        /(webkit)[ \/]([\w.]+)/.exec( ua ) ||
                        /(opera)(?:.*version|)[ \/]([\w.]+)/.exec( ua ) ||
                        /(msie) ([\w.]+)/.exec( ua ) ||
                        ua.indexOf("trident") >= 0 && /(rv)(?::| )([\w.]+)/.exec( ua ) ||
                        ua.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec( ua ) ||
                        [];

                    var platform_match = /(ipad)/.exec( ua ) ||
                        /(iphone)/.exec( ua ) ||
                        /(android)/.exec( ua ) ||
                        /(windows phone)/.exec( ua ) ||
                        /(win)/.exec( ua ) ||
                        /(mac)/.exec( ua ) ||
                        /(linux)/.exec( ua ) ||
                        /(cros)/i.exec( ua ) ||
                        [];

                    return {
                        browser: match[ 3 ] || match[ 1 ] || "",
                        version: match[ 2 ] || "0",
                        platform: platform_match[ 0 ] || ""
                    };
                };

                matched = jQuery.uaMatch( window.navigator.userAgent );
                browser = {};

                if ( matched.browser ) {
                    browser[ matched.browser ] = true;
                    browser.version = matched.version;
                    browser.versionNumber = parseInt(matched.version);
                }

                if ( matched.platform ) {
                    browser[ matched.platform ] = true;
                }

                // These are all considered mobile platforms, meaning they run a mobile browser
                if ( browser.android || browser.ipad || browser.iphone || browser[ "windows phone" ] ) {
                    browser.mobile = true;
                }

                // These are all considered desktop platforms, meaning they run a desktop browser
                if ( browser.cros || browser.mac || browser.linux || browser.win ) {
                    browser.desktop = true;
                }

                // Chrome, Opera 15+ and Safari are webkit based browsers
                if ( browser.chrome || browser.opr || browser.safari ) {
                    browser.webkit = true;
                }

                // IE11 has a new token so we will assign it msie to avoid breaking changes
                if ( browser.rv )
                {
                    var ie = "msie";

                    matched.browser = ie;
                    browser[ie] = true;
                }

                // Opera 15+ are identified as opr
                if ( browser.opr )
                {
                    var opera = "opera";

                    matched.browser = opera;
                    browser[opera] = true;
                }

                // Stock Android browsers are marked as Safari on Android.
                if ( browser.safari && browser.android )
                {
                    var android = "android";

                    matched.browser = android;
                    browser[android] = true;
                }

                // Assign the name and platform variable
                browser.name = matched.browser;
                browser.platform = matched.platform;

                this.browser = browser;
                $.browser = browser;
            }
        },

        isIE: function(version) {
            return (version === undefined) ? this.browser.msie: (this.browser.msie && parseInt(this.browser.version, 10) === version);
        },

        isLtIE: function(version) {
            return (this.browser.msie) ? parseInt(this.browser.version, 10) < version : false;
        }
    };

    PrimeFaces.env.init();

};
if (!PrimeFaces.ajax) {

    /**
     * AJAX parameter shortcut mapping for PrimeFaces.ab
     */
    PrimeFaces.AB_MAPPING = {
        's': 'source',
        'f': 'formId',
        'p': 'process',
        'u': 'update',
        'e': 'event',
        'a': 'async',
        'g': 'global',
        'd': 'delay',
        't': 'timeout',
        'sc': 'skipChildren',
        'iau': 'ignoreAutoUpdate',
        'ps': 'partialSubmit',
        'psf': 'partialSubmitFilter',
        'rv': 'resetValues',
        'fi': 'fragmentId',
        'fu': 'fragmentUpdate',
        'pa': 'params',
        'onst': 'onstart',
        'oner': 'onerror',
        'onsu': 'onsuccess',
        'onco': 'oncomplete'
    };

    /**
     * Ajax shortcut
     */
    PrimeFaces.ab = function(cfg, ext) {
        for (var option in cfg) {
            if (!cfg.hasOwnProperty(option)) {
                continue;
            }

            // just pass though if no mapping is available
            if (this.AB_MAPPING[option]) {
                cfg[this.AB_MAPPING[option]] = cfg[option];
                delete cfg[option];
            }
        }

        PrimeFaces.ajax.Request.handle(cfg, ext);
    };

    PrimeFaces.ajax = {

        VIEW_HEAD : "javax.faces.ViewHead",
        VIEW_BODY : "javax.faces.ViewBody",
        RESOURCE : "javax.faces.Resource",

        Utils: {

            loadStylesheets: function(stylesheets) {
                for (var i = 0; i < stylesheets.length; i++) {
                    $('head').append('<link type="text/css" rel="stylesheet" href="' + stylesheets[i] + '" />');
                }
            },

            loadScripts: function(scripts) {
                var loadNextScript = function() {
                    var script = scripts.shift();
                    if (script) {
                        PrimeFaces.getScript(script, loadNextScript);
                    }
                };

                loadNextScript();
            },

            getContent: function(node) {
                var content = '';

                for(var i = 0; i < node.childNodes.length; i++) {
                    content += node.childNodes[i].nodeValue;
                }

                return content;
            },

            updateFormStateInput: function(name, value, xhr) {
                var trimmedValue = $.trim(value);

                var forms = null;
                if (xhr && xhr.pfSettings && xhr.pfSettings.portletForms) {
                    forms = $(xhr.pfSettings.portletForms);
                }
                else {
                    forms = $('form');
                }

                var parameterPrefix = '';
                if (xhr && xhr.pfArgs && xhr.pfArgs.parameterPrefix) {
                    parameterPrefix = xhr.pfArgs.parameterPrefix;
                }

                for (var i = 0; i < forms.length; i++) {
                    var form = forms.eq(i);

                    if (form.attr('method') === 'post') {
                        var input = form.children("input[name='" + parameterPrefix + name + "']");

                        if (input.length > 0) {
                            input.val(trimmedValue);
                        } else {
                            form.append('<input type="hidden" name="' + parameterPrefix + name + '" value="' + trimmedValue + '" autocomplete="off" />');
                        }
                    }
                }
            },

            updateHead: function(content) {
                var cache = $.ajaxSetup()['cache'];
                $.ajaxSetup()['cache'] = true;

                var headStartTag = new RegExp("<head[^>]*>", "gi").exec(content)[0];
                var headStartIndex = content.indexOf(headStartTag) + headStartTag.length;
                $('head').html(content.substring(headStartIndex, content.lastIndexOf("</head>")));

                $.ajaxSetup()['cache'] = cache;
            },

            updateBody: function(content) {
                var bodyStartTag = new RegExp("<body[^>]*>", "gi").exec(content)[0];
                var bodyStartIndex = content.indexOf(bodyStartTag) + bodyStartTag.length;
                $('body').html(content.substring(bodyStartIndex, content.lastIndexOf("</body>")));
            },

            updateElement: function(id, content, xhr) {

                if (id.indexOf(PrimeFaces.VIEW_STATE) !== -1) {
                    PrimeFaces.ajax.Utils.updateFormStateInput(PrimeFaces.VIEW_STATE, content, xhr);
                }
                else if (id.indexOf(PrimeFaces.CLIENT_WINDOW) !== -1) {
                    PrimeFaces.ajax.Utils.updateFormStateInput(PrimeFaces.CLIENT_WINDOW, content, xhr);
                }
                // used by @all
                else if (id === PrimeFaces.VIEW_ROOT) {

                    // backup our utils, we reset it soon
                    var ajaxUtils = PrimeFaces.ajax.Utils;

                    // reset PrimeFaces JS state because the view is completely replaced with a new one
                    window.PrimeFaces = null;

                    ajaxUtils.updateHead(content);
                    ajaxUtils.updateBody(content);
                }
                else if (id === PrimeFaces.ajax.VIEW_HEAD) {
                    PrimeFaces.ajax.Utils.updateHead(content);
                }
                else if (id === PrimeFaces.ajax.VIEW_BODY) {
                    PrimeFaces.ajax.Utils.updateBody(content);
                }
                else if (id === PrimeFaces.ajax.RESOURCE) {
                    $('head').append(content);
                }
                else if (id === $('head')[0].id) {
                    PrimeFaces.ajax.Utils.updateHead(content);
                }
                else {
                    $(PrimeFaces.escapeClientId(id)).replaceWith(content);
                }
            }
        },

        Queue: {

            delays: {},

            requests: new Array(),

            xhrs: new Array(),

            offer: function(request) {
                if(request.delay) {
                    var sourceId = null,
                    $this = this,
                    sourceId = (typeof(request.source) === 'string') ? request.source: $(request.source).attr('id'),
                    createTimeout = function() {
                            return setTimeout(function() {
                                $this.requests.push(request);

                                if($this.requests.length === 1) {
                                    PrimeFaces.ajax.Request.send(request);
                                }
                            }, request.delay);
                    };

                    if(this.delays[sourceId]) {
                        clearTimeout(this.delays[sourceId].timeout);
                        this.delays[sourceId].timeout = createTimeout();
                    }
                    else {
                        this.delays[sourceId] = {
                            timeout: createTimeout()
                        };
                    }
                }
                else {
                    this.requests.push(request);

                    if(this.requests.length === 1) {
                        PrimeFaces.ajax.Request.send(request);
                    }
                }
            },

            poll: function() {
                if(this.isEmpty()) {
                    return null;
                }

                var processed = this.requests.shift(),
                next = this.peek();

                if(next) {
                    PrimeFaces.ajax.Request.send(next);
                }

                return processed;
            },

            peek: function() {
                if(this.isEmpty()) {
                    return null;
                }

                return this.requests[0];
            },

            isEmpty: function() {
                return this.requests.length === 0;
            },

            addXHR: function(xhr) {
                this.xhrs.push(xhr);
            },

            removeXHR: function(xhr) {
                var index = $.inArray(xhr, this.xhrs);
                if(index > -1) {
                    this.xhrs.splice(index, 1);
                }
            },

            abortAll: function() {
                for(var i = 0; i < this.xhrs.length; i++) {
                    this.xhrs[i].abort();
                }

                this.xhrs = new Array();
                this.requests = new Array();
            }
        },

        Request: {

            handle: function(cfg, ext) {
                cfg.ext = ext;

                if (PrimeFaces.settings.earlyPostParamEvaluation) {
                    cfg.earlyPostParams = PrimeFaces.ajax.Request.collectEarlyPostParams(cfg);
                }

                if(cfg.async) {
                    PrimeFaces.ajax.Request.send(cfg);
                }
                else {
                    PrimeFaces.ajax.Queue.offer(cfg);
                }
            },

            collectEarlyPostParams: function(cfg) {

                var earlyPostParams;

                var sourceElement;
                if (typeof(cfg.source) === 'string') {
                    sourceElement = $(PrimeFaces.escapeClientId(cfg.source));
                }
                else {
                    sourceElement = $(cfg.source);
                }
                if (sourceElement.is(':input') && sourceElement.is(':not(:button)')) {
                    earlyPostParams = [];

                    if (sourceElement.is(':checkbox')) {
                        var checkboxPostParams = $("input[name='" + sourceElement.attr('name') + "']")
                                .filter(':checked').serializeArray();
                        $.merge(earlyPostParams, checkboxPostParams);
                    }
                    else {
                        earlyPostParams.push({
                            name: sourceElement.attr('name'),
                            value: sourceElement.val()
                        });
                    }
                }
                else {
                    earlyPostParams = sourceElement.serializeArray();
                }

                return earlyPostParams;
            },

            send: function(cfg) {
                PrimeFaces.debug('Initiating ajax request.');

                PrimeFaces.customFocus = false;

                var global = (cfg.global === true || cfg.global === undefined) ? true : false,
                form = null,
                sourceId = null,
                retVal = null;

                if(cfg.onstart) {
                    retVal = cfg.onstart.call(this, cfg);
                }
                if(cfg.ext && cfg.ext.onstart) {
                    retVal = cfg.ext.onstart.call(this, cfg);
                }

                if(retVal === false) {
                    PrimeFaces.debug('Ajax request cancelled by onstart callback.');

                    //remove from queue
                    if(!cfg.async) {
                        PrimeFaces.ajax.Queue.poll();
                    }

                    return false;  //cancel request
                }

                if(global) {
                    $(document).trigger('pfAjaxStart');
                }

                //source can be a client id or an element defined by this keyword
                if(typeof(cfg.source) === 'string') {
                    sourceId = cfg.source;
                } else {
                    sourceId = $(cfg.source).attr('id');
                }

                if(cfg.formId) {
                    //Explicit form is defined
                    form = PrimeFaces.expressions.SearchExpressionFacade.resolveComponentsAsSelector(cfg.formId);
                }
                else {
                    var $source = $(PrimeFaces.escapeClientId(sourceId));
                    //look for a parent of source
                    form = $source.closest('form');

                    //source has no parent form so use first form in document
                    if (form.length === 0) {
                        form = $('form').eq(0);
                    }
                }

                PrimeFaces.debug('Form to post ' + form.attr('id') + '.');

                var postURL = form.attr('action'),
                encodedURLfield = form.children("input[name*='javax.faces.encodedURL']"),
                postParams = [];

                // See #6857 - parameter namespace for porlet
                var parameterPrefix = PrimeFaces.ajax.Request.extractParameterNamespace(form);

                //portlet support
                var portletFormsSelector = null;
                if(encodedURLfield.length > 0) {
                    portletFormsSelector = 'form[id*="' + parameterPrefix + '"]';
                    postURL = encodedURLfield.val();
                }

                PrimeFaces.debug('URL to post ' + postURL + '.');

                //partial ajax
                PrimeFaces.ajax.Request.addParam(postParams, PrimeFaces.PARTIAL_REQUEST_PARAM, true, parameterPrefix);

                //source
                PrimeFaces.ajax.Request.addParam(postParams, PrimeFaces.PARTIAL_SOURCE_PARAM, sourceId, parameterPrefix);

                //resetValues
                if (cfg.resetValues) {
                    PrimeFaces.ajax.Request.addParam(postParams, PrimeFaces.RESET_VALUES_PARAM, true, parameterPrefix);
                }

                //ignoreAutoUpdate
                if (cfg.ignoreAutoUpdate) {
                    PrimeFaces.ajax.Request.addParam(postParams, PrimeFaces.IGNORE_AUTO_UPDATE_PARAM, true, parameterPrefix);
                }

                //skip children
                if (cfg.skipChildren === false) {
                    PrimeFaces.ajax.Request.addParam(postParams, PrimeFaces.SKIP_CHILDREN_PARAM, false, parameterPrefix);
                }

                //process
                var processArray = PrimeFaces.ajax.Request.resolveComponentsForAjaxCall(cfg, 'process');
                if(cfg.fragmentId) {
                    processArray.push(cfg.fragmentId);
                }
                // default == @none
                var processIds = '@none';
                // use defined process + resolved keywords (@widget, PFS)?
                if (processArray.length > 0) {
                    processIds = processArray.join(' ');
                }
                // fallback to @all if no process was defined by the user
                else {
                    var definedProcess = PrimeFaces.ajax.Request.resolveComponentsForAjaxCall(cfg, 'process');
                    definedProcess = $.trim(definedProcess);
                    if (definedProcess === '') {
                        processIds = '@all';
                    }
                }
                if (processIds !== '@none') {
                    PrimeFaces.ajax.Request.addParam(postParams, PrimeFaces.PARTIAL_PROCESS_PARAM, processIds, parameterPrefix);
                }

                //update
                var updateArray = PrimeFaces.ajax.Request.resolveComponentsForAjaxCall(cfg, 'update');
                if(cfg.fragmentId && cfg.fragmentUpdate) {
                    updateArray.push(cfg.fragmentId);
                }
                if(updateArray.length > 0) {
                    PrimeFaces.ajax.Request.addParam(postParams, PrimeFaces.PARTIAL_UPDATE_PARAM, updateArray.join(' '), parameterPrefix);
                }

                //behavior event
                if(cfg.event) {
                    PrimeFaces.ajax.Request.addParam(postParams, PrimeFaces.BEHAVIOR_EVENT_PARAM, cfg.event, parameterPrefix);

                    var domEvent = cfg.event;

                    if(cfg.event === 'valueChange')
                        domEvent = 'change';
                    else if(cfg.event === 'action')
                        domEvent = 'click';

                    PrimeFaces.ajax.Request.addParam(postParams, PrimeFaces.PARTIAL_EVENT_PARAM, domEvent, parameterPrefix);
                }
                else {
                    PrimeFaces.ajax.Request.addParam(postParams, sourceId, sourceId, parameterPrefix);
                }

                //params
                if(cfg.params) {
                    PrimeFaces.ajax.Request.addParams(postParams, cfg.params, parameterPrefix);
                }
                if(cfg.ext && cfg.ext.params) {
                    PrimeFaces.ajax.Request.addParams(postParams, cfg.ext.params, parameterPrefix);
                }

                /**
                 * Only add params of process components and their children
                 * if partial submit is enabled and there are components to process partially
                 */
                if(cfg.partialSubmit && processIds.indexOf('@all') === -1) {
                    var formProcessed = false,
                    partialSubmitFilter = cfg.partialSubmitFilter||':input';

                    if(processIds.indexOf('@none') === -1) {
                        for (var i = 0; i < processArray.length; i++) {
                            var jqProcess = $(PrimeFaces.escapeClientId(processArray[i]));
                            var componentPostParams = null;

                            if(jqProcess.is('form')) {
                                componentPostParams = jqProcess.serializeArray();
                                formProcessed = true;
                            }
                            else if(jqProcess.is(':input')) {
                                componentPostParams = jqProcess.serializeArray();
                            }
                            else {
                                componentPostParams = jqProcess.find(partialSubmitFilter).serializeArray();
                            }

                            $.merge(postParams, componentPostParams);
                        }
                    }

                    //add form state if necessary
                    if (!formProcessed) {
                        PrimeFaces.ajax.Request.addParamFromInput(postParams, PrimeFaces.VIEW_STATE, form, parameterPrefix);
                        PrimeFaces.ajax.Request.addParamFromInput(postParams, PrimeFaces.CLIENT_WINDOW, form, parameterPrefix);
                        PrimeFaces.ajax.Request.addParamFromInput(postParams, 'dsPostWindowId', form, parameterPrefix);
                        PrimeFaces.ajax.Request.addParamFromInput(postParams, 'dspwid', form, parameterPrefix);
                    }

                }
                else {
                    $.merge(postParams, form.serializeArray());
                }

                // remove postParam if already available in earlyPostParams
                if (PrimeFaces.settings.earlyPostParamEvaluation && cfg.earlyPostParams) {
                    // loop early post params
                    $.each(cfg.earlyPostParams, function(earlyPostParamIndex, earlyPostParam) {
                        // loop post params and remove it, if it's the same param as the early post param
                        postParams = $.grep(postParams, function(postParam, postParamIndex) {
                            if (postParam.name === earlyPostParam.name) {
                                return false;
                            }
                            return true;
                        });
                    });

                    $.merge(postParams, cfg.earlyPostParams);
                }

                //serialize
                var postData = $.param(postParams);

                PrimeFaces.debug('Post Data:' + postData);

                var xhrOptions = {
                    url : postURL,
                    type : "POST",
                    cache : false,
                    dataType : "xml",
                    data : postData,
                    portletForms: portletFormsSelector,
                    source: cfg.source,
                    global: false,
                    beforeSend: function(xhr, settings) {
                        xhr.setRequestHeader('Faces-Request', 'partial/ajax');
                        xhr.pfSettings = settings;
                        xhr.pfArgs = {}; // default should be an empty object
                        PrimeFaces.nonAjaxPosted = false;

                        if(global) {
                            $(document).trigger('pfAjaxSend', [xhr, this]);
                        }
                    }
                };

                if (cfg.timeout) {
                    xhrOptions['timeout'] = cfg.timeout;
                }

                var jqXhr = $.ajax(xhrOptions)
                    .fail(function(xhr, status, errorThrown) {
                        if(cfg.onerror) {
                            cfg.onerror.call(this, xhr, status, errorThrown);
                        }
                        if(cfg.ext && cfg.ext.onerror) {
                            cfg.ext.onerror.call(this, xhr, status, errorThrown);
                        }

                        $(document).trigger('pfAjaxError', [xhr, this, errorThrown]);

                        PrimeFaces.error('Request return with error:' + status + '.');
                    })
                    .done(function(data, status, xhr) {
                        PrimeFaces.debug('Response received succesfully.');

                        try {
                            var parsed;

                            //call user callback
                            if(cfg.onsuccess) {
                                parsed = cfg.onsuccess.call(this, data, status, xhr);
                            }

                            //extension callback that might parse response
                            if(cfg.ext && cfg.ext.onsuccess && !parsed) {
                                parsed = cfg.ext.onsuccess.call(this, data, status, xhr);
                            }

                            if(global) {
                                $(document).trigger('pfAjaxSuccess', [xhr, this]);
                            }

                            //do not execute default handler as response already has been parsed
                            if(parsed) {
                                return;
                            }
                            else {
                                PrimeFaces.ajax.Response.handle(data, status, xhr);
                            }
                        }
                        catch(err) {
                            PrimeFaces.error(err);
                        }

                        PrimeFaces.debug('DOM is updated.');
                    })
                    .always(function(data, status, xhr) {
                        // first call the extension callback (e.g. datatable paging)
                        if(cfg.ext && cfg.ext.oncomplete) {
                            cfg.ext.oncomplete.call(this, xhr, status, xhr.pfArgs);
                        }

                        // after that, call the endusers callback, which should be called when everything is ready
                        if(cfg.oncomplete) {
                            cfg.oncomplete.call(this, xhr, status, xhr.pfArgs);
                        }

                        if(global) {
                            $(document).trigger('pfAjaxComplete', [xhr, this]);
                        }

                        PrimeFaces.debug('Response completed.');

                        PrimeFaces.ajax.Queue.removeXHR(xhr);

                        if(!cfg.async && !PrimeFaces.nonAjaxPosted) {
                            PrimeFaces.ajax.Queue.poll();
                        }
                    });

                PrimeFaces.ajax.Queue.addXHR(jqXhr);
            },

            resolveExpressionsForAjaxCall: function(cfg, type) {
                var expressions = '';

                if (cfg[type]) {
                    expressions += cfg[type];
                }

                if (cfg.ext && cfg.ext[type]) {
                    expressions += " " + cfg.ext[type];
                }

                return expressions;
            },

            resolveComponentsForAjaxCall: function(cfg, type) {
                var expressions = PrimeFaces.ajax.Request.resolveExpressionsForAjaxCall(cfg, type);
                return PrimeFaces.expressions.SearchExpressionFacade.resolveComponents(expressions);
            },

            addParam: function(params, name, value, parameterPrefix) {
                // add namespace if not available
                if (parameterPrefix || !name.indexOf(parameterPrefix) === 0) {
                    params.push({ name:parameterPrefix + name, value:value });
                }
                else {
                    params.push({ name:name, value:value });
                }

            },

            addParams: function(params, paramsToAdd, parameterPrefix) {

                for (var i = 0; i < paramsToAdd.length; i++) {
                    var param = paramsToAdd[i];
                    // add namespace if not available
                    if (parameterPrefix && !param.name.indexOf(parameterPrefix) === 0) {
                        param.name = parameterPrefix + param.name;
                    }

                    params.push(param);
                }
            },

            addParamFromInput: function(params, name, form, parameterPrefix) {
                var input = null;
                if (parameterPrefix) {
                    input = form.children("input[name*='" + name + "']");
                }
                else {
                    input = form.children("input[name='" + name + "']");
                }

                if (input && input.length > 0) {
                    var value = input.val();
                    PrimeFaces.ajax.Request.addParam(params, name, value, parameterPrefix);
                }
            },

            extractParameterNamespace: function(form) {
                var input = form.children("input[name*='" + PrimeFaces.VIEW_STATE + "']");
                if (input && input.length > 0) {
                    var name = input[0].name;
                    if (name.length > PrimeFaces.VIEW_STATE.length) {
                        return name.substring(0, name.indexOf(PrimeFaces.VIEW_STATE));
                    }
                }

                return null;
            }
        },

        Response: {

            handle: function(xml, status, xhr, updateHandler) {
                var partialResponseNode = xml.getElementsByTagName("partial-response")[0];

                for (var i = 0; i < partialResponseNode.childNodes.length; i++) {
                    var currentNode = partialResponseNode.childNodes[i];

                    switch (currentNode.nodeName) {
                        case "redirect":
                            PrimeFaces.ajax.ResponseProcessor.doRedirect(currentNode);
                            break;

                        case "changes":
                            var activeElement = $(document.activeElement);
                            var activeElementId = activeElement.attr('id');
                            var activeElementSelection;
                            if (activeElement.length > 0 && activeElement.is('input') && $.isFunction($.fn.getSelection)) {
                                activeElementSelection = activeElement.getSelection();
                            }

                            for (var j = 0; j < currentNode.childNodes.length; j++) {
                                var currentChangeNode = currentNode.childNodes[j];
                                switch (currentChangeNode.nodeName) {
                                    case "update":
                                        PrimeFaces.ajax.ResponseProcessor.doUpdate(currentChangeNode, xhr, updateHandler);
                                        break;
                                    case "delete":
                                        PrimeFaces.ajax.ResponseProcessor.doDelete(currentChangeNode);
                                        break;
                                    case "insert":
                                        PrimeFaces.ajax.ResponseProcessor.doInsert(currentChangeNode);
                                        break;
                                    case "attributes":
                                        PrimeFaces.ajax.ResponseProcessor.doAttributes(currentChangeNode);
                                        break;
                                    case "eval":
                                        PrimeFaces.ajax.ResponseProcessor.doEval(currentChangeNode);
                                        break;
                                    case "extension":
                                        PrimeFaces.ajax.ResponseProcessor.doExtension(currentChangeNode, xhr);
                                        break;
                                }
                            }

                            PrimeFaces.ajax.Response.handleReFocus(activeElementId, activeElementSelection);
                            PrimeFaces.ajax.Response.destroyDetachedWidgets();
                            break;

                        case "eval":
                            PrimeFaces.ajax.ResponseProcessor.doEval(currentNode);
                            break;

                        case "extension":
                            PrimeFaces.ajax.ResponseProcessor.doExtension(currentNode, xhr);
                            break;

                        case "error":
                            PrimeFaces.ajax.ResponseProcessor.doError(currentNode, xhr);
                            break;
                    }
                }
            },

            handleReFocus : function(activeElementId, activeElementSelection) {

                // re-focus element
                if (PrimeFaces.customFocus === false
                        && activeElementId
                        // do we really need to refocus? we just check the current activeElement here
                        && activeElementId !== $(document.activeElement).attr('id')) {

                    var elementToFocus = $(PrimeFaces.escapeClientId(activeElementId));
                    var refocus = function() {
                        elementToFocus.focus();

                        if (activeElementSelection && activeElementSelection.start) {
                            elementToFocus.setSelection(activeElementSelection.start, activeElementSelection.end);
                        }
                    };

                    if(elementToFocus.length) {
                        refocus();

                        // double check it - required for IE
                        setTimeout(function() {
                            if (!elementToFocus.is(":focus")) {
                                refocus();
                            }
                        }, 50);
                    }
                }

                PrimeFaces.customFocus = false;
            },

            destroyDetachedWidgets : function() {
                // destroy detached widgets
                for (var i = 0; i < PrimeFaces.detachedWidgets.length; i++) {
                    var widgetVar = PrimeFaces.detachedWidgets[i];

                    var widget = PF(widgetVar);
                    if (widget) {
                        if (widget.isDetached()) {
                            PrimeFaces.widgets[widgetVar] = null;
                            widget.destroy();

                            try {
                                delete widget;
                            } catch (e) {}
                        }
                    }
                }

                PrimeFaces.detachedWidgets = [];
            }
        },

        ResponseProcessor: {

            doRedirect : function(node) {
                try {
                    window.location.assign(node.getAttribute('url'));
                } catch (error) {
                    PrimeFaces.warn('Error redirecting to URL: ' + node.getAttribute('url'));
                }
            },

            doUpdate : function(node, xhr, updateHandler) {
                var id = node.getAttribute('id'),
                content = PrimeFaces.ajax.Utils.getContent(node);

                if (updateHandler && updateHandler.widget && updateHandler.widget.id === id) {
                    updateHandler.handle.call(updateHandler.widget, content);
                } else {
                    PrimeFaces.ajax.Utils.updateElement(id, content, xhr);
                }
            },

            doEval : function(node) {
                var textContent = node.textContent || node.innerText || node.text;
                $.globalEval(textContent);
            },

            doExtension : function(node, xhr) {
                if (xhr) {
                    if (node.getAttribute("ln") === "primefaces" && node.getAttribute("type") === "args") {
                        var textContent = node.textContent || node.innerText || node.text;
                        // it's possible that pfArgs are already defined e.g. if portlet parameter namespacing is enabled
                        // the "parameterPrefix" will be encoded on document start
                        // the other parameters will be encoded on document end
                        // --> see PrimePartialResponseWriter
                        if (xhr.pfArgs) {
                            var json = JSON.parse(textContent);
                            for (var name in json) {
                                xhr.pfArgs[name] = json[name];
                            }
                        }
                        else {
                            xhr.pfArgs = JSON.parse(textContent);
                        }
                    }
                }
            },

            doError : function(node, xhr) {
                // currently nothing...
            },

            doDelete : function(node) {
                var id = node.getAttribute('id');
                $(PrimeFaces.escapeClientId(id)).remove();
            },

            doInsert : function(node) {
                if (!node.childNodes) {
                    return false;
                }

                for (var i = 0; i < node.childNodes.length; i++) {
                    var childNode = node.childNodes[i];
                    var id = childNode.getAttribute('id');
                    var jq = $(PrimeFaces.escapeClientId(id));
                    var content = PrimeFaces.ajax.Utils.getContent(childNode);

                    if (childNode.nodeName === "after") {
                        $(content).insertAfter(jq);
                    }
                    else if (childNode.nodeName === "before") {
                        $(content).insertBefore(jq);
                    }
                }
            },

            doAttributes : function(node) {
                if (!node.childNodes) {
                    return false;
                }

                var id = node.getAttribute('id');
                var jq = $(PrimeFaces.escapeClientId(id));

                for (var i = 0; i < node.childNodes.length; i++) {
                    var attrNode = node.childNodes[i];
                    var attrName = attrNode.getAttribute("name");
                    var attrValue = attrNode.getAttribute("value");

                    if (!attrName) {
                        return;
                    }

                    if (!attrValue || attrValue === null) {
                        attrValue = "";
                    }

                    jq.attr(attrName, attrValue);
                }
            }
        },

        //Backward compatibility
        AjaxRequest: function(cfg, ext) {
            return PrimeFaces.ajax.Request.handle(cfg, ext);
        }
    };

    $(window).on('beforeunload', function() {
        PrimeFaces.ajax.Queue.abortAll();
    });

}
if (!PrimeFaces.expressions) {

    PrimeFaces.expressions = {};

    PrimeFaces.expressions.SearchExpressionFacade = {

        resolveComponentsAsSelector: function(expressions) {

            var splittedExpressions = PrimeFaces.expressions.SearchExpressionFacade.splitExpressions(expressions);
            var elements = $();

            if (splittedExpressions) {
                for (var i = 0; i < splittedExpressions.length; ++i) {
                    var expression =  $.trim(splittedExpressions[i]);
                    if (expression.length > 0) {

                        // skip unresolvable keywords
                        if (expression == '@none' || expression == '@all') {
                            continue;
                        }

                        // just a id
                        if (expression.indexOf("@") == -1) {
                            elements = elements.add(
                                    $(document.getElementById(expression)));
                        }
                        // @widget
                        else if (expression.indexOf("@widgetVar(") == 0) {
                            var widgetVar = expression.substring(11, expression.length - 1);
                            var widget = PrimeFaces.widgets[widgetVar];

                            if (widget) {
                                elements = elements.add(
                                        $(document.getElementById(widget.id)));
                            } else {
                                PrimeFaces.error("Widget for widgetVar \"" + widgetVar + "\" not avaiable");
                            }
                        }
                        // PFS
                        else if (expression.indexOf("@(") == 0) {
                            //converts pfs to jq selector e.g. @(div.mystyle :input) to div.mystyle :input
                            elements = elements.add(
                                    $(expression.substring(2, expression.length - 1)));
                        }
                    }
                }
            }

            return elements;
        },

        resolveComponents: function(expressions) {
            var splittedExpressions = PrimeFaces.expressions.SearchExpressionFacade.splitExpressions(expressions),
            ids = [];

            if (splittedExpressions) {
                for (var i = 0; i < splittedExpressions.length; ++i) {
                    var expression =  $.trim(splittedExpressions[i]);
                    if (expression.length > 0) {

                        // just a id or passtrough keywords
                        if (expression.indexOf("@") == -1 || expression == '@none' || expression == '@all') {
                            if (!PrimeFaces.inArray(ids, expression)) {
                                ids.push(expression);
                            }
                        }
                        // @widget
                        else if (expression.indexOf("@widgetVar(") == 0) {
                            var widgetVar = expression.substring(11, expression.length - 1),
                            widget = PrimeFaces.widgets[widgetVar];

                            if (widget) {
                                if (!PrimeFaces.inArray(ids, widget.id)) {
                                    ids.push(widget.id);
                                }
                            } else {
                                PrimeFaces.error("Widget for widgetVar \"" + widgetVar + "\" not avaiable");
                            }
                        }
                        // PFS
                        else if (expression.indexOf("@(") == 0) {
                            //converts pfs to jq selector e.g. @(div.mystyle :input) to div.mystyle :input
                            var elements = $(expression.substring(2, expression.length - 1));

                            for (var j = 0; j < elements.length; j++) {
                                var element = $(elements[j]),
                                clientId = element.data(PrimeFaces.CLIENT_ID_DATA) || element.attr('id');

                                if (!PrimeFaces.inArray(ids, clientId)) {
                                    ids.push(clientId);
                                }
                            }
                        }
                    }
                }
            }

            return ids;
        },

        splitExpressions: function(expression) {

            var expressions = [];
            var buffer = '';

            var parenthesesCounter = 0;

            for (var i = 0; i < expression.length; i++) {
                var c = expression[i];

                if (c === '(') {
                    parenthesesCounter++;
                }

                if (c === ')') {
                    parenthesesCounter--;
                }

                if ((c === ' ' || c === ',') && parenthesesCounter === 0) {
                    // lets add token inside buffer to our tokens
                    expressions.push(buffer);
                    // now we need to clear buffer
                    buffer = '';
                } else {
                    buffer += c;
                }
            }

            // lets not forget about part after the separator
            expressions.push(buffer);

            return expressions;
        }
    };
}
/* Simple JavaScript Inheritance
 * By John Resig http://ejohn.org/
 * MIT Licensed.
 */
// Inspired by base2 and Prototype
(function(){
  var initializing = false, fnTest = /xyz/.test(function(){xyz;}) ? /\b_super\b/ : /.*/;
  // The base Class implementation (does nothing)
  this.Class = function(){};

  // Create a new Class that inherits from this class
  Class.extend = function(prop) {
    var _super = this.prototype;

    // Instantiate a base class (but only create the instance,
    // don't run the init constructor)
    initializing = true;
    var prototype = new this();
    initializing = false;

    // Copy the properties over onto the new prototype
    for (var name in prop) {
      // Check if we're overwriting an existing function
      prototype[name] = typeof prop[name] == "function" && 
        typeof _super[name] == "function" && fnTest.test(prop[name]) ?
        (function(name, fn){
          return function() {
            var tmp = this._super;

            // Add a new ._super() method that is the same method
            // but on the super-class
            this._super = _super[name];

            // The method only need to be bound temporarily, so we
            // remove it when we're done executing
            var ret = fn.apply(this, arguments);        
            this._super = tmp;

            return ret;
          };
        })(name, prop[name]) :
        prop[name];
    }

    // The dummy class constructor
    function Class() {
      // All construction is actually done in the init method
      if ( !initializing && this.init )
        this.init.apply(this, arguments);
    }

    // Populate our constructed prototype object
    Class.prototype = prototype;

    // Enforce the constructor to be what we expect
    Class.prototype.constructor = Class;

    // And make this class extendable
    Class.extend = arguments.callee;

    return Class;
  };
})();

if (!PrimeFaces.widget) {

    PrimeFaces.widget = {};

    /**
     * BaseWidget for PrimeFaces Widgets
     */
    PrimeFaces.widget.BaseWidget = Class.extend({

        init: function(cfg) {
            this.cfg = cfg;
            this.id = cfg.id;
            this.jqId = PrimeFaces.escapeClientId(this.id);
            this.jq = $(this.jqId);
            this.widgetVar = cfg.widgetVar;

            //remove script tag
            $(this.jqId + '_s').remove();

            if (this.widgetVar) {
                var $this = this;
                this.jq.on("remove", function() {
                    if ($this.isDetached()) {
                        PrimeFaces.detachedWidgets.push($this.widgetVar);
                    }
                });
            }
        },

        //used in ajax updates, reloads the widget configuration
        refresh: function(cfg) {
            return this.init(cfg);
        },

        //will be called when the widget after a ajax request if the widget is detached
        destroy: function() {
            PrimeFaces.debug("Destroyed detached widget: " + this.widgetVar);
        },

        //checks if the given widget is detached
        isDetached: function() {
            return document.getElementById(this.id) === null;
        },

        //returns jquery object representing the main dom element related to the widget
        getJQ: function(){
            return this.jq;
        },

        /**
         * Removes the widget's script block from the DOM.
         *
         * @param {string} clientId The id of the widget.
         */
        removeScriptElement: function(clientId) {
            $(PrimeFaces.escapeClientId(clientId) + '_s').remove();
        },
        
        hasBehavior: function(event) {
            if(this.cfg.behaviors) {
                return this.cfg.behaviors[event] != undefined;
            }

            return false;
        },

    });

    /**
     * Widgets that require to be visible to initialize properly for hidden container support
     */
    PrimeFaces.widget.DeferredWidget = PrimeFaces.widget.BaseWidget.extend({

        renderDeferred: function() {     
            if(this.jq.is(':visible')) {
                this._render();
                this.postRender();
            }
            else {
                var container = this.jq.closest('.ui-hidden-container'),
                $this = this;

                if(container.length) {
                    this.addDeferredRender(this.id, container, function() {
                        return $this.render();
                    });
                }
            }
        },

        render: function() {
            if(this.jq.is(':visible')) {
                this._render();
                this.postRender();
                return true;
            }
            else {
                return false;
            }
        },

        /**
         * Must be overriden
         */
        _render: function() {
            throw 'Unsupported Operation';
        },

        postRender: function() {

        },

        destroy: function() {
            this._super();
            PrimeFaces.removeDeferredRenders(this.id);
        },

        addDeferredRender: function(widgetId, container, callback) {
            PrimeFaces.addDeferredRender(widgetId, container.attr('id'), callback);

            if(container.is(':hidden')) {
                var parentContainer = this.jq.closest('.ui-hidden-container');

                if(parentContainer.length) {
                    this.addDeferredRender(widgetId, container.parent().closest('.ui-hidden-container'), callback);
                }
            }
        }
    });
}
/**
 * PrimeFaces AjaxStatus Widget
 */
PrimeFaces.widget.AjaxStatus = PrimeFaces.widget.BaseWidget.extend({
             
    init: function(cfg) {
        this._super(cfg);

        this.bind();
    },
            
    bind: function() {
        var doc = $(document),
        $this = this;
        
        doc.on('pfAjaxStart', function() {
            $this.trigger('start', arguments);
        })
        .on('pfAjaxError', function() {
            $this.trigger('error', arguments);
        })
        .on('pfAjaxSuccess', function() {
            $this.trigger('success', arguments);
        })
        .on('pfAjaxComplete', function() {
            $this.trigger('complete', arguments);
        });
        
        this.bindToStandard();
    },
            
    trigger: function(event, args) {
        var callback = this.cfg[event];
        if(callback) {
            callback.apply(document, args);
        }
        
        this.jq.children().hide().filter(this.jqId + '_' + event).show();
    },
         
    bindToStandard: function() {
        if(window.jsf && window.jsf.ajax) {
        	var doc = $(document);

        	jsf.ajax.addOnEvent(function(data) {
                if(data.status === 'begin') {
                    doc.trigger('pfAjaxStart', arguments);
                }
                else if(data.status === 'complete') {
                    doc.trigger('pfAjaxSuccess', arguments);
                }
                else if(data.status === 'success') {
                    doc.trigger('pfAjaxComplete', arguments);
                }
            });

            jsf.ajax.addOnError(function(data) {
                doc.trigger('pfAjaxError', arguments);
            });
        }
    }
    
});
/**
 * PrimeFaces Poll Widget
 */
PrimeFaces.widget.Poll = PrimeFaces.widget.BaseWidget.extend({

    init: function(cfg) {
        this.cfg = cfg;
        this.id = this.cfg.id;
        this.active = false;

        if(this.cfg.autoStart) {
            this.start();
        }
    },
    
    refresh: function(cfg) {
        if(this.isActive()) {
            this.stop();
        }
        
        this.init(cfg);
    },

    start: function() {
        if (!this.active) {
            this.timer = setInterval(this.cfg.fn, (this.cfg.frequency * 1000));
            this.active = true;
        }
    },

    stop: function() {
        if (this.active) {
            clearInterval(this.timer);
            this.active = false;
        }
    },

    isActive: function() {
        return this.active;
    }
});
