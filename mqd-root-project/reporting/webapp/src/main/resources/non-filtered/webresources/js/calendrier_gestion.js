
/**
 * PrimeFaces Schedule Widget
 */
PrimeFaces.widget.Schedule = PrimeFaces.widget.DeferredWidget.extend({

    init: function(cfg) {
        this._super(cfg);
        this.cfg.formId = this.jq.closest('form').attr('id');
        this.cfg.theme = true;
        this.jqc = $(this.jqId + '_container');
        this.viewNameState = $(this.jqId + '_view');
        this.cfg.urlTarget = this.cfg.urlTarget || "_blank";

        if(this.cfg.defaultDate) {
            this.cfg.defaultDate = moment(this.cfg.defaultDate);
        }

        this.setupEventSource();

        this.configureLocale();

        if(this.cfg.tooltip) {
            this.tip = $('<div class="ui-tooltip ui-widget ui-widget-content ui-shadow ui-corner-all"></div>').appendTo(this.jq);
        }

        this.setupEventHandlers();

        if(this.cfg.extender) {
            this.cfg.extender.call(this);
        }

        this.setViewOptions();

        this.renderDeferred();
    },

    _render: function() {
        this.jqc.fullCalendar(this.cfg);

        this.bindViewChangeListener();
    },

    configureLocale: function() {
        var lang = PrimeFaces.locales[this.cfg.locale];

        if(lang) {
            this.cfg.firstDay = lang.firstDay;
            this.cfg.monthNames = lang.monthNames;
            this.cfg.monthNamesShort = lang.monthNamesShort;
            this.cfg.dayNames = lang.dayNames;
            this.cfg.dayNamesShort = lang.dayNamesShort;
            this.cfg.buttonText = {today: lang.currentText
                                    ,month: lang.month
                                    ,week: lang.week
                                    ,day: lang.day};
            this.cfg.allDayText = lang.allDayText;
            if(lang.eventLimitText) {
                this.cfg.eventLimitText = lang.eventLimitText;
            }
            // BEGINNING : CHANGED PART
            this.cfg.axisFormat = 'HH:mm';
            this.cfg.timeFormat = 'HH:mm';
            this.cfg.weekNumberTitle = 'S';
            this.cfg.titleFormat = {
                    month: 'MMMM YYYY',
                    week: 'DD MMMM YYYY'
             };
             this.cfg.columnFormat = {
                    month: 'dddd',
                    week: 'dddd DD/MM'
             };
             // END : CHANGED PART
        }
    },

    setupEventHandlers: function() {
        var $this = this;

        this.cfg.dayClick = function(dayDate, jsEvent, view) {
            if($this.cfg.behaviors) {
                var dateSelectBehavior = $this.cfg.behaviors['dateSelect'];
                if(dateSelectBehavior) {
                    var ext = {
                        params: [
							{name: $this.id + '_selectedDate', value: dayDate.valueOf() - dayDate.zone()*60000}
                        ]
                    };

                    dateSelectBehavior.call($this, ext);
                }
            }
        };

        this.cfg.eventClick = function(calEvent, jsEvent, view) {
            if (calEvent.url) {
                window.open(calEvent.url, $this.cfg.urlTarget);
                return false;
            }

            if($this.cfg.behaviors) {
                var eventSelectBehavior = $this.cfg.behaviors['eventSelect'];
                if(eventSelectBehavior) {
                    var ext = {
                        params: [
                            {name: $this.id + '_selectedEventId', value: calEvent.id}
                        ]
                    };

                    eventSelectBehavior.call($this, ext);
                }
            }
        };

        this.cfg.eventDrop = function(calEvent, delta, revertFunc, jsEvent, ui, view) {
            if($this.cfg.behaviors) {
                var eventMoveBehavior = $this.cfg.behaviors['eventMove'];
                if(eventMoveBehavior) {
                    var ext = {
                        params: [
                            {name: $this.id + '_movedEventId', value: calEvent.id},
                            {name: $this.id + '_dayDelta', value: delta.days()},
                            {name: $this.id + '_minuteDelta', value: (delta._milliseconds/60000)}
                        ]
                    };

                    eventMoveBehavior.call($this, ext);
                }
            }
        };

        this.cfg.eventResize = function(calEvent, delta, revertFunc, jsEvent, ui, view) {
            if($this.cfg.behaviors) {
                var eventResizeBehavior = $this.cfg.behaviors['eventResize'];
                if(eventResizeBehavior) {
                    var ext = {
                        params: [
                            {name: $this.id + '_resizedEventId', value: calEvent.id},
                            {name: $this.id + '_dayDelta', value: delta.days()},
                            {name: $this.id + '_minuteDelta', value: (delta._milliseconds/60000)}
                        ]
                    };

                    eventResizeBehavior.call($this, ext);
                }
            }
        };

        if(this.cfg.tooltip) {
            this.cfg.eventMouseover = function(event, jsEvent, view) {
                if(event.description) {
                    $this.tipTimeout = setTimeout(function() {
                        $this.tip.css({
                            'left': jsEvent.pageX,
                            'top': jsEvent.pageY + 15,
                            'z-index': ++PrimeFaces.zindex
                        })
                        .html(event.description)
                        .show();
                    }, 150);
                }
            };

            this.cfg.eventMouseout = function(event, jsEvent, view) {
                if($this.tipTimeout) {
                    clearTimeout($this.tipTimeout);
                }

                if($this.tip.is(':visible')) {
                    $this.tip.hide();
                    $this.tip.text('');
                }
            };
        }
    },

    setupEventSource: function() {
        var $this = this,
        offset = moment().zone()*60000;

        this.cfg.events = function(start, end, timezone, callback) {
            var options = {
                source: $this.id,
                process: $this.id,
                update: $this.id,
                formId: $this.cfg.formId,
                params: [
                    {name: $this.id + '_start', value: start.valueOf() + offset},
                    {name: $this.id + '_end', value: end.valueOf() + offset}
                ],
                onsuccess: function(responseXML, status, xhr) {
                    PrimeFaces.ajax.Response.handle(responseXML, status, xhr, {
                            widget: $this,
                            handle: function(content) {
                                callback($.parseJSON(content).events);
                            }
                        });

                    return true;
                }
            };

            PrimeFaces.ajax.Request.handle(options);
        };
    },

    update: function() {
        this.jqc.fullCalendar('refetchEvents');
    },

    bindViewChangeListener: function() {
    	// BEGINNING : CHANGED PART
        // var viewButtons = this.jqc.find('> .fc-toolbar button:not(.fc-prev-button,.fc-next-button,.fc-today-button)'),
        var viewButtons = this.jqc.find('> .fc-toolbar button'),
        $this = this;
        // END : CHANGED PART

        viewButtons.each(function(i) {
            var viewButton = viewButtons.eq(i),
            buttonClasses = viewButton.attr('class').split(' ');
            for(var i = 0; i < buttonClasses.length; i++) {
                var buttonClassParts = buttonClasses[i].split('-');
                if(buttonClassParts.length === 3) {
                    viewButton.data('view', buttonClassParts[1]);
                    break;
                }
            }
        });

        viewButtons.on('click.schedule', function() {
            var viewName = $(this).data('view');
            
            $this.viewNameState.val(viewName);
            
            // BEGINNING : CHANGED PART
            var viewAttributes = $this.jqc.fullCalendar('getView');
            
            var startDate = viewAttributes.intervalStart.format('DD/MM/YYYY');
            $('#formCalendrierGestion\\:startDate').val(startDate);
            $('#formCalendrierGestion\\:startDate').change();
            
            var endDate = viewAttributes.intervalEnd.format('DD/MM/YYYY');
            $('#formCalendrierGestion\\:endDate').val(endDate);
            $('#formCalendrierGestion\\:endDate').change();
            // END : CHANGED PART
            
            if($this.cfg.behaviors) {
                var viewChangeBehavior = $this.cfg.behaviors['viewChange'];
                if(viewChangeBehavior) {
                    viewChangeBehavior.call($this);
                }
            }
        });
    },

    setViewOptions: function() {
        var views = {
            month: {},       // month view
            week: {},        // basicWeek & agendaWeek views
            day: {},         // basicDay & agendaDay views
            agenda: {},      // agendaDay & agendaWeek views
            agendaDay: {},   // agendaDay view
            agendaWeek: {}   // agendaWeek view
        };

        var columnFormat = this.cfg.columnFormatOptions;
        if(columnFormat) {
            for (var view in views) {
                views[view] = {columnFormat: columnFormat[view]};
            }
        }

        this.cfg.views = views;
    }

});
