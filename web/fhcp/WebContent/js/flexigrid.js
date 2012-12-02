/*
 * Flexigrid for jQuery -  v1.1
 *
 * Copyright (c) 2008 Paulo P. Marinas (code.google.com/p/flexigrid/)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 */
var provinces=",����,�Ϻ�,���,����, �ӱ�,ɽ��,���ɹ�,����,����,������,����,�㽭,����,����,����,ɽ��,����,����,����,�㶫,����,����,�Ĵ�,����,����,����,����,����,����,�ຣ,�½�,���,����,̨��";
var province=provinces.split(",");

(function ($) {
	$.addFlex = function (t, p) {
		if (t.grid) return false; //return if already exist
		p = $.extend({ //apply default properties
			height: 200, //default height
			width: 'auto', //auto width
			striped: true, //apply odd even stripes
			novstripe: false,
			minwidth: 30, //min width of columns
			minheight: 80, //min height of columns
			resizable: true, //allow table resizing
			url: false, //URL if using data from AJAX
			method: 'POST', //data sending method
			dataType: 'xml', //type of data for AJAX, either xml or json
			errormsg: 'Connection Error',
			usepager: false,
			nowrap: true,
			page: 1, //current page
			total: 1, //total pages
			useRp: true, //use the results per page select box
			rp: 15, //results per page
			rpOptions: [10, 15, 20, 30, 50], //allowed per-page values 
			title: false,
			pagestat: 'Displaying {from} to {to} of {total} items',
			pagetext: 'Page',
			outof: 'of',
			findtext: 'Find',
			procmsg: 'Processing, please wait ...',
			query: '',
			qtype: '',
			cnamevalue:'',
			csexvalue:'',
			cage1value:'',
		    cage2value:'',
		    cidcardvalue:'',
		    ctitlevalue:'',
		    cprovincevalue:'',
		    ccityvalue:'',
		    cpostvalue:'',
		    cmobilevalue:'',
		    cfixvalue:'',
		    cidvalue:'',
		    caddrvalue:'',
		    ccompanyvalue:'',
		    ctypevalue:'',
		    ctypelogic:'',
		    csourcevalue:'',
		    cothervalue:'',
		    cservidvalue:'',
			searchtype:'all',
			nomsg: 'No items',
			minColToggle: 1, //minimum allowed column to be hidden
			showToggleBtn: true, //show or hide column toggle popup
			hideOnSubmit: true,
			autoload: true,
			blockOpacity: 0.5,
			preProcess: false,
			onDragCol: false,
			onToggleCol: false,
			onChangeSort: false,
			onSuccess: false,
			onError: false,
			onSubmit: false //using a custom populate function
		}, p);
		$(t).show() //show if hidden
			.attr({
				cellPadding: 0,
				cellSpacing: 0,
				border: 0
			}) //remove padding and spacing
			.removeAttr('width'); //remove width properties
		//create grid class
		var g = {
			hset: {},
			rePosDrag: function () {
				var cdleft = 0 - this.hDiv.scrollLeft;
				if (this.hDiv.scrollLeft > 0) cdleft -= Math.floor(p.cgwidth / 2);
				$(g.cDrag).css({
					top: g.hDiv.offsetTop + 1
				});
				var cdpad = this.cdpad;
				$('div', g.cDrag).hide();
				$('thead tr:first th:visible', this.hDiv).each(function () {
					var n = $('thead tr:first th:visible', g.hDiv).index(this);
					var cdpos = parseInt($('div', this).width());
					if (cdleft == 0) cdleft -= Math.floor(p.cgwidth / 2);
					cdpos = cdpos + cdleft + cdpad;
					if (isNaN(cdpos)) {
						cdpos = 0;
					}
					$('div:eq(' + n + ')', g.cDrag).css({
						'left': cdpos + 'px'
					}).show();
					cdleft = cdpos;
				});
			},
			fixHeight: function (newH) {
				newH = false;
				if (!newH) newH = $(g.bDiv).height();
				var hdHeight = $(this.hDiv).height();
				$('div', this.cDrag).each(
					function () {
						$(this).height(newH + hdHeight);
					}
				);
				var nd = parseInt($(g.nDiv).height());
				if (nd > newH) $(g.nDiv).height(newH).width(200);
				else $(g.nDiv).height('auto').width('auto');
				$(g.block).css({
					height: newH,
					marginBottom: (newH * -1)
				});
				var hrH = g.bDiv.offsetTop + newH;
				if (p.height != 'auto' && p.resizable) hrH = g.vDiv.offsetTop;
				$(g.rDiv).css({
					height: hrH
				});
			},
			dragStart: function (dragtype, e, obj) { //default drag function start
				if (dragtype == 'colresize') {//column resize
					$(g.nDiv).hide();
					$(g.nBtn).hide();
					var n = $('div', this.cDrag).index(obj);
					var ow = $('th:visible div:eq(' + n + ')', this.hDiv).width();
					$(obj).addClass('dragging').siblings().hide();
					$(obj).prev().addClass('dragging').show();
					this.colresize = {
						startX: e.pageX,
						ol: parseInt(obj.style.left),
						ow: ow,
						n: n
					};
					$('body').css('cursor', 'col-resize');
				} else if (dragtype == 'vresize') {//table resize
					var hgo = false;
					$('body').css('cursor', 'row-resize');
					if (obj) {
						hgo = true;
						$('body').css('cursor', 'col-resize');
					}
					this.vresize = {
						h: p.height,
						sy: e.pageY,
						w: p.width,
						sx: e.pageX,
						hgo: hgo
					};
				} else if (dragtype == 'colMove') {//column header drag
					$(g.nDiv).hide();
					$(g.nBtn).hide();
					this.hset = $(this.hDiv).offset();
					this.hset.right = this.hset.left + $('table', this.hDiv).width();
					this.hset.bottom = this.hset.top + $('table', this.hDiv).height();
					this.dcol = obj;
					this.dcoln = $('th', this.hDiv).index(obj);
					this.colCopy = document.createElement("div");
					this.colCopy.className = "colCopy";
					this.colCopy.innerHTML = obj.innerHTML;
					if ($.browser.msie) {
						this.colCopy.className = "colCopy ie";
					}
					$(this.colCopy).css({
						position: 'absolute',
						float: 'left',
						display: 'none',
						textAlign: obj.align
					});
					$('body').append(this.colCopy);
					$(this.cDrag).hide();
				}
				$('body').noSelect();
			},
			dragMove: function (e) {
				if (this.colresize) {//column resize
					var n = this.colresize.n;
					var diff = e.pageX - this.colresize.startX;
					var nleft = this.colresize.ol + diff;
					var nw = this.colresize.ow + diff;
					if (nw > p.minwidth) {
						$('div:eq(' + n + ')', this.cDrag).css('left', nleft);
						this.colresize.nw = nw;
					}
				} else if (this.vresize) {//table resize
					var v = this.vresize;
					var y = e.pageY;
					var diff = y - v.sy;
					if (!p.defwidth) p.defwidth = p.width;
					if (p.width != 'auto' && !p.nohresize && v.hgo) {
						var x = e.pageX;
						var xdiff = x - v.sx;
						var newW = v.w + xdiff;
						if (newW > p.defwidth) {
							this.gDiv.style.width = newW + 'px';
							p.width = newW;
						}
					}
					var newH = v.h + diff;
					if ((newH > p.minheight || p.height < p.minheight) && !v.hgo) {
						this.bDiv.style.height = newH + 'px';
						p.height = newH;
						this.fixHeight(newH);
					}
					v = null;
				} else if (this.colCopy) {
					$(this.dcol).addClass('thMove').removeClass('thOver');
					if (e.pageX > this.hset.right || e.pageX < this.hset.left || e.pageY > this.hset.bottom || e.pageY < this.hset.top) {
						//this.dragEnd();
						$('body').css('cursor', 'move');
					} else {
						$('body').css('cursor', 'pointer');
					}
					$(this.colCopy).css({
						top: e.pageY + 10,
						left: e.pageX + 20,
						display: 'block'
					});
				}
			},
			dragEnd: function () {
				if (this.colresize) {
					var n = this.colresize.n;
					var nw = this.colresize.nw;
					$('th:visible div:eq(' + n + ')', this.hDiv).css('width', nw);
					$('tr', this.bDiv).each(
						function () {
							$('td:visible div:eq(' + n + ')', this).css('width', nw);
						}
					);
					this.hDiv.scrollLeft = this.bDiv.scrollLeft;
					$('div:eq(' + n + ')', this.cDrag).siblings().show();
					$('.dragging', this.cDrag).removeClass('dragging');
					this.rePosDrag();
					this.fixHeight();
					this.colresize = false;
				} else if (this.vresize) {
					this.vresize = false;
				} else if (this.colCopy) {
					$(this.colCopy).remove();
					if (this.dcolt != null) {
						if (this.dcoln > this.dcolt) $('th:eq(' + this.dcolt + ')', this.hDiv).before(this.dcol);
						else $('th:eq(' + this.dcolt + ')', this.hDiv).after(this.dcol);
						this.switchCol(this.dcoln, this.dcolt);
						$(this.cdropleft).remove();
						$(this.cdropright).remove();
						this.rePosDrag();
						if (p.onDragCol) {
							p.onDragCol(this.dcoln, this.dcolt);
						}
					}
					this.dcol = null;
					this.hset = null;
					this.dcoln = null;
					this.dcolt = null;
					this.colCopy = null;
					$('.thMove', this.hDiv).removeClass('thMove');
					$(this.cDrag).show();
				}
				$('body').css('cursor', 'default');
				$('body').noSelect(false);
			},
			toggleCol: function (cid, visible) {
				var ncol = $("th[axis='col" + cid + "']", this.hDiv)[0];
				var n = $('thead th', g.hDiv).index(ncol);
				var cb = $('input[value=' + cid + ']', g.nDiv)[0];
				if (visible == null) {
					visible = ncol.hidden;
				}
				if ($('input:checked', g.nDiv).length < p.minColToggle && !visible) {
					return false;
				}
				if (visible) {
					ncol.hidden = false;
					$(ncol).show();
					cb.checked = true;
				} else {
					ncol.hidden = true;
					$(ncol).hide();
					cb.checked = false;
				}
				$('tbody tr', t).each(
					function () {
						if (visible) {
							$('td:eq(' + n + ')', this).show();
						} else {
							$('td:eq(' + n + ')', this).hide();
						}
					}
				);
				this.rePosDrag();
				if (p.onToggleCol) {
					p.onToggleCol(cid, visible);
				}
				return visible;
			},
			switchCol: function (cdrag, cdrop) { //switch columns
				$('tbody tr', t).each(
					function () {
						if (cdrag > cdrop) $('td:eq(' + cdrop + ')', this).before($('td:eq(' + cdrag + ')', this));
						else $('td:eq(' + cdrop + ')', this).after($('td:eq(' + cdrag + ')', this));
					}
				);
				//switch order in nDiv
				if (cdrag > cdrop) {
					$('tr:eq(' + cdrop + ')', this.nDiv).before($('tr:eq(' + cdrag + ')', this.nDiv));
				} else {
					$('tr:eq(' + cdrop + ')', this.nDiv).after($('tr:eq(' + cdrag + ')', this.nDiv));
				}
				if ($.browser.msie && $.browser.version < 7.0) {
					$('tr:eq(' + cdrop + ') input', this.nDiv)[0].checked = true;
				}
				this.hDiv.scrollLeft = this.bDiv.scrollLeft;
			},
			scroll: function () {
				this.hDiv.scrollLeft = this.bDiv.scrollLeft;
				this.rePosDrag();
			},
			addData: function (data) { //parse data
				if (p.dataType == 'json') {
					data = $.extend({rows: [], page: 0, total: 0}, data);
				}
				if (p.preProcess) {
					data = p.preProcess(data);
				}
				$('.pReload', this.pDiv).removeClass('loading');
				this.loading = false;
				if (!data) {
					$('.pPageStat', this.pDiv).html(p.errormsg);
					return false;
				}
				if (p.dataType == 'xml') {
					p.total = +$('rows total', data).text();
				} else {
					p.total = data.total;
				}
				if (p.total == 0) {
					$('tr, a, td, div', t).unbind();
					$(t).empty();
					p.pages = 1;
					p.page = 1;
					this.buildpager();
					$('.pPageStat', this.pDiv).html(p.nomsg);
					return false;
				}
				p.pages = Math.ceil(p.total / p.rp);
				if (p.dataType == 'xml') {
					p.page = +$('rows page', data).text();
				} else {
					p.page = data.page;
				}
				this.buildpager();
				//build new body
				var tbody = document.createElement('tbody');
				if (p.dataType == 'json') {
					$.each(data.rows, function (i, row) {
						var tr = document.createElement('tr');
						if (i % 2 && p.striped) {
							tr.className = 'erow';
						}
						if (row.id) {
							tr.id = 'row' + row.id;
						}
						$('thead tr:first th', g.hDiv).each( //add cell
							function () {
								var td = document.createElement('td');
								var idx = $(this).attr('axis').substr(3);
								td.align = this.align;
								// If the json elements aren't named (which is typical), use numeric order
								if (typeof row.cell[idx] != "undefined") {
									td.innerHTML = (row.cell[idx] != null) ? row.cell[idx] : '';//null-check for Opera-browser
								} else {
									td.innerHTML = row.cell[p.colModel[idx].name];
								}
								$(td).attr('abbr', $(this).attr('abbr'));
								$(tr).append(td);
								td = null;
							}
						);
						if ($('thead', this.gDiv).length < 1) {//handle if grid has no headers
							for (idx = 0; idx < cell.length; idx++) {
								var td = document.createElement('td');
								// If the json elements aren't named (which is typical), use numeric order
								if (typeof row.cell[idx] != "undefined") {
									td.innerHTML = (row.cell[idx] != null) ? row.cell[idx] : '';//null-check for Opera-browser
								} else {
									td.innerHTML = row.cell[p.colModel[idx].name];
								}
								$(tr).append(td);
								td = null;
							}
						}
						$(tbody).append(tr);
						tr = null;
					});
				} else if (p.dataType == 'xml') {
					var i = 1;
					$("rows row", data).each(function () {
						i++;
						var tr = document.createElement('tr');
						if (i % 2 && p.striped) {
							tr.className = 'erow';
						}
						var nid = $(this).attr('id');
						if (nid) {
							tr.id = 'row' + nid;
						}
						nid = null;
						var robj = this;
						$('thead tr:first th', g.hDiv).each(function () {
							var td = document.createElement('td');
							var idx = $(this).attr('axis').substr(3);
							td.align = this.align;
							td.innerHTML = $("cell:eq(" + idx + ")", robj).text();
							$(td).attr('abbr', $(this).attr('abbr'));
							$(tr).append(td);
							td = null;
						});
						if ($('thead', this.gDiv).length < 1) {//handle if grid has no headers
							$('cell', this).each(function () {
								var td = document.createElement('td');
								td.innerHTML = $(this).text();
								$(tr).append(td);
								td = null;
							});
						}
						$(tbody).append(tr);
						tr = null;
						robj = null;
					});
				}
				$('tr', t).unbind();
				$(t).empty();
				$(t).append(tbody);
				this.addCellProp();
				this.addRowProp();
				this.rePosDrag();
				tbody = null;
				data = null;
				i = null;
				if (p.onSuccess) {
					p.onSuccess(this);
				}
				if (p.hideOnSubmit) {
					$(g.block).remove();
				}
				this.hDiv.scrollLeft = this.bDiv.scrollLeft;
				if ($.browser.opera) {
					$(t).css('visibility', 'visible');
				}
			},
			changeSort: function (th) { //change sortorder
				if (this.loading) {
					return true;
				}
				$(g.nDiv).hide();
				$(g.nBtn).hide();
				if (p.sortname == $(th).attr('abbr')) {
					if (p.sortorder == 'asc') {
						p.sortorder = 'desc';
					} else {
						p.sortorder = 'asc';
					}
				}
				$(th).addClass('sorted').siblings().removeClass('sorted');
				$('.sdesc', this.hDiv).removeClass('sdesc');
				$('.sasc', this.hDiv).removeClass('sasc');
				$('div', th).addClass('s' + p.sortorder);
				p.sortname = $(th).attr('abbr');
				if (p.onChangeSort) {
					p.onChangeSort(p.sortname, p.sortorder);
				} else {
					this.populate();
				}
			},
			buildpager: function () { //rebuild pager based on new properties
				$('.pcontrol input', this.pDiv).val(p.page);
				$('.pcontrol span', this.pDiv).html(p.pages);
				var r1 = (p.page - 1) * p.rp + 1;
				var r2 = r1 + p.rp - 1;
				if (p.total < r2) {
					r2 = p.total;
				}
				var stat = p.pagestat;
				stat = stat.replace(/{from}/, r1);
				stat = stat.replace(/{to}/, r2);
				stat = stat.replace(/{total}/, p.total);
				$('.pPageStat', this.pDiv).html(stat);
			},
			constructfilter: function()
			{			    
				var filterStr = '';
				var param = [{
					name: 'page',
					value: p.newp
				}, {
					name: 'rp',
					value: p.rp
				}, {
					name: 'sortname',
					value: p.sortname
				}, {
					name: 'sortorder',
					value: p.sortorder
				}, {
					name: 'query',
					value: p.query
				}, {
					name: 'qtype',
					value: p.qtype
				}, {
					name: 'cnamevalue',
					value: p.cnamevalue
				},{
					name: 'csexvalue',
					value: p.csexvalue
				},{
					name: 'searchtype',
					value: p.searchtype
				},{
					name: 'cage1value',
					value: p.cage1value
				},{
					name: 'cage2value',
					value: p.cage2value
				},{
					name: 'cidcardvalue',
					value: p.cidcardvalue
				},{
					name: 'ctitlevalue',
					value: p.ctitlevalue
				},{
					name: 'cprovincevalue',
					value: p.cprovincevalue
				},{
					name: 'ccityvalue',
					value: p.ccityvalue
				},{
					name: 'cpostvalue',
					value: p.cpostvalue
				},{
					name: 'cmobilevalue',
					value: p.cmobilevalue
				},{
					name: 'cfixvalue',
					value: p.cfixvalue
				},{			
					name: 'cmailvalue',
					value: p.cmailvalue
				},{	
					name: 'cidvalue',
					value: p.cidvalue
				},{
					name: 'caddrvalue',
					value: p.caddrvalue
				},{
					name: 'ccompanyvalue',
					value: p.ccompanyvalue
				},{
					name: 'ctypevalue',
					value: p.ctypevalue
				},{
					name: 'ctypelogic',
					value: p.ctypelogic
				},{
					name: 'csourcevalue',
					value: p.csourcevalue
				},{
					name: 'cothervalue',
					value: p.cothervalue					
				},{
					name: 'cservicestate',
					value: p.cservicestate					
				},{
					name: 'cservidvalue',
					value: p.cservidvalue
				}
				
				];
				for (var pi = 0; pi < param.length; pi++)
				{
					filterStr += '&'+param[pi].name+'='+encodeURI(encodeURI(param[pi].value));
				}
				return filterStr;
			},
			populate: function () { //get latest data
				if (this.loading) {
					return true;
				}
				if (p.onSubmit) {
					var gh = p.onSubmit();
					if (!gh) {
						return false;
					}
				}
				this.loading = true;
				if (!p.url) {
					return false;
				}
				$('.pPageStat', this.pDiv).html(p.procmsg);
				$('.pReload', this.pDiv).addClass('loading');
				$(g.block).css({
					top: g.bDiv.offsetTop
				});
				if (p.hideOnSubmit) {
					$(this.gDiv).prepend(g.block);
				}
				if ($.browser.opera) {
					$(t).css('visibility', 'hidden');
				}
				if (!p.newp) {
					p.newp = 1;
				}
				if (p.page > p.pages) {
					p.page = p.pages;
				}
				var param = [{
					name: 'page',
					value: p.newp
				}, {
					name: 'rp',
					value: p.rp
				}, {
					name: 'sortname',
					value: p.sortname
				}, {
					name: 'sortorder',
					value: p.sortorder
				}, {
					name: 'query',
					value: p.query
				}, {
					name: 'qtype',
					value: p.qtype
				}, {
					name: 'cnamevalue',
					value: p.cnamevalue
				},{
					name: 'csexvalue',
					value: p.csexvalue
				},{
					name: 'searchtype',
					value: p.searchtype
				},{
					name: 'cage1value',
					value: p.cage1value
				},{
					name: 'cage2value',
					value: p.cage2value
				},{
					name: 'cidcardvalue',
					value: p.cidcardvalue
				},{
					name: 'ctitlevalue',
					value: p.ctitlevalue
				},{
					name: 'cprovincevalue',
					value: p.cprovincevalue
				},{
					name: 'ccityvalue',
					value: p.ccityvalue
				},{
					name: 'cpostvalue',
					value: p.cpostvalue
				},{
					name: 'cfixvalue',
					value: p.cfixvalue
				},{
					name: 'cmailvalue',
					value: p.cmailvalue
				},{
					name: 'cmobilevalue',
					value: p.cmobilevalue
				},{
					name: 'cidvalue',
					value: p.cidvalue
				},{
					name: 'caddrvalue',
					value: p.caddrvalue
				},{
					name: 'ccompanyvalue',
					value: p.ccompanyvalue
				},{
					name: 'ctypevalue',
					value: p.ctypevalue
				},{
					name: 'ctypelogic',
					value: p.ctypelogic
				},{
					name: 'csourcevalue',
					value: p.csourcevalue
				},{
					name: 'cothervalue',
					value: p.cothervalue
				},{
					name: 'cservicestate',
					value: p.cservicestate
				},{
					name: 'cservidvalue',
					value: p.cservidvalue
				}
				];
				if (p.params) {
					for (var pi = 0; pi < p.params.length; pi++) {
						param[param.length] = p.params[pi];
					}
				}
				$.ajax({
					type: p.method,
					url: p.url,
					data: param,
					dataType: p.dataType,
					success: function (data) {
						g.addData(data);
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
						try {
							if (p.onError) p.onError(XMLHttpRequest, textStatus, errorThrown);
						} catch (e) {}
					}
				});
			},
			doSearch: function () {
				p.query = $('input[name=q]', g.sDiv).val();
				p.qtype = $('select[name=qtype]', g.sDiv).val();
				p.searchtype="normal";
				p.newp = 1;
				this.populate();
			},
			changePage: function (ctype) { //change page
				if (this.loading) {
					return true;
				}
				switch (ctype) {
					case 'first':
						p.newp = 1;
						break;
					case 'prev':
						if (p.page > 1) {
							p.newp = parseInt(p.page) - 1;
						}
						break;
					case 'next':
						if (p.page < p.pages) {
							p.newp = parseInt(p.page) + 1;
						}
						break;
					case 'last':
						p.newp = p.pages;
						break;
					case 'input':
						var nv = parseInt($('.pcontrol input', this.pDiv).val());
						if (isNaN(nv)) {
							nv = 1;
						}
						if (nv < 1) {
							nv = 1;
						} else if (nv > p.pages) {
							nv = p.pages;
						}
						$('.pcontrol input', this.pDiv).val(nv);
						p.newp = nv;
						break;
				}
				if (p.newp == p.page) {
					return false;
				}
				if (p.onChangePage) {
					p.onChangePage(p.newp);
				} else {
					this.populate();
				}
			},
			addCellProp: function () {
				$('tbody tr td', g.bDiv).each(function () {
					var tdDiv = document.createElement('div');
					var n = $('td', $(this).parent()).index(this);
					var pth = $('th:eq(' + n + ')', g.hDiv).get(0);
					if (pth != null) {
						if (p.sortname == $(pth).attr('abbr') && p.sortname) {
							this.className = 'sorted';
						}
						$(tdDiv).css({
							textAlign: pth.align,
							width: $('div:first', pth)[0].style.width
						});
						if (pth.hidden) {
							$(this).css('display', 'none');
						}
					}
					if (p.nowrap == false) {
						$(tdDiv).css('white-space', 'normal');
					}
					if (this.innerHTML == '') {
						this.innerHTML = '&nbsp;';
					}
					tdDiv.innerHTML = this.innerHTML;
					var prnt = $(this).parent()[0];
					var pid = false;
					if (prnt.id) {
						pid = prnt.id.substr(3);
					}
					if (pth != null) {
						if (pth.process) pth.process(tdDiv, pid);
					}
					$(this).empty().append(tdDiv).removeAttr('width'); //wrap content
				});
			},
			getCellDim: function (obj) {// get cell prop for editable event
				var ht = parseInt($(obj).height());
				var pht = parseInt($(obj).parent().height());
				var wt = parseInt(obj.style.width);
				var pwt = parseInt($(obj).parent().width());
				var top = obj.offsetParent.offsetTop;
				var left = obj.offsetParent.offsetLeft;
				var pdl = parseInt($(obj).css('paddingLeft'));
				var pdt = parseInt($(obj).css('paddingTop'));
				return {
					ht: ht,
					wt: wt,
					top: top,
					left: left,
					pdl: pdl,
					pdt: pdt,
					pht: pht,
					pwt: pwt
				};
			},
			addRowProp: function () {
				$('tbody tr', g.bDiv).each(function () {
					$(this).click(function (e) {
						var obj = (e.target || e.srcElement);
						if (obj.href || obj.type) return true;
						$(this).toggleClass('trSelected');
						if (p.singleSelect) $(this).siblings().removeClass('trSelected');
					}).mousedown(function (e) {
						if (e.shiftKey) {
							$(this).toggleClass('trSelected');
							g.multisel = true;
							this.focus();
							$(g.gDiv).noSelect();
						}
					}).mouseup(function () {
						if (g.multisel) {
							g.multisel = false;
							$(g.gDiv).noSelect(false);
						}
					}).hover(function (e) {
						if (g.multisel) {
							$(this).toggleClass('trSelected');
						}
					}, function () {});
					if ($.browser.msie && $.browser.version < 7.0) {
						$(this).hover(function () {
							$(this).addClass('trOver');
						}, function () {
							$(this).removeClass('trOver');
						});
					}
				});
			},
			pager: 0
		};
		
		if (p.colModel) { //create model if any
			thead = document.createElement('thead');
			var tr = document.createElement('tr');
			for (var i = 0; i < p.colModel.length; i++) {
				var cm = p.colModel[i];
				var th = document.createElement('th');
				th.innerHTML = cm.display;
				if (cm.name && cm.sortable) {
					$(th).attr('abbr', cm.name);
				}
				$(th).attr('axis', 'col' + i);
				if (cm.align) {
					th.align = cm.align;
				}
				if (cm.width) {
					$(th).attr('width', cm.width);
				}
				if ($(cm).attr('hide')) {
					th.hidden = true;
				}
				if (cm.process) {
					th.process = cm.process;
				}
				$(tr).append(th);
			}
			$(thead).append(tr);
			$(t).prepend(thead);
		} // end if p.colmodel
		//init divs
		g.gDiv = document.createElement('div'); //create global container
		g.mDiv = document.createElement('div'); //create title container
		g.hDiv = document.createElement('div'); //create header container
		g.bDiv = document.createElement('div'); //create body container
		g.vDiv = document.createElement('div'); //create grip
		g.rDiv = document.createElement('div'); //create horizontal resizer
		g.cDrag = document.createElement('div'); //create column drag
		g.block = document.createElement('div'); //creat blocker
		g.nDiv = document.createElement('div'); //create column show/hide popup
		g.nBtn = document.createElement('div'); //create column show/hide button
		g.iDiv = document.createElement('div'); //create editable layer
		g.tDiv = document.createElement('div'); //create toolbar
		g.sDiv = document.createElement('div');
		g.s1Div = document.createElement('div');
		g.pDiv = document.createElement('div'); //create pager container
		if (!p.usepager) {
			g.pDiv.style.display = 'none';
		}
		g.hTable = document.createElement('table');
		g.gDiv.className = 'flexigrid';
		if (p.width != 'auto') {
			g.gDiv.style.width = p.width + 'px';
		}
		//add conditional classes
		if ($.browser.msie) {
			$(g.gDiv).addClass('ie');
		}
		if (p.novstripe) {
			$(g.gDiv).addClass('novstripe');
		}
		$(t).before(g.gDiv);
		$(g.gDiv).append(t);
		//set toolbar
		if (p.buttons) {
			g.tDiv.className = 'tDiv';
			var tDiv2 = document.createElement('div');
			tDiv2.className = 'tDiv2';
			for (var i = 0; i < p.buttons.length; i++) {
				var btn = p.buttons[i];
				if (!btn.separator) {
					var btnDiv = document.createElement('div');
					btnDiv.className = 'fbutton';
					btnDiv.innerHTML = "<div><span>" + btn.name + "</span></div>";
					if (btn.bclass) $('span', btnDiv).addClass(btn.bclass).css({
						paddingLeft: 20
					});
					btnDiv.onpress = btn.onpress;
					btnDiv.name = btn.name;
					if (btn.onpress) {
						$(btnDiv).click(function () {
							this.onpress(this.name, g.gDiv);
						});
					}
					$(tDiv2).append(btnDiv);
					if ($.browser.msie && $.browser.version < 7.0) {
						$(btnDiv).hover(function () {
							$(this).addClass('fbOver');
						}, function () {
							$(this).removeClass('fbOver');
						});
					}
				} else {
					$(tDiv2).append("<div class='btnseparator'></div>");
				}
			}
			$(g.tDiv).append(tDiv2);
			$(g.tDiv).append("<div style='clear:both'></div>");
			$(g.gDiv).prepend(g.tDiv);
		}
		g.hDiv.className = 'hDiv';
		$(t).before(g.hDiv);
		g.hTable.cellPadding = 0;
		g.hTable.cellSpacing = 0;
		$(g.hDiv).append('<div class="hDivBox"></div>');
		$('div', g.hDiv).append(g.hTable);
		var thead = $("thead:first", t).get(0);
		if (thead) $(g.hTable).append(thead);
		thead = null;
		if (!p.colmodel) var ci = 0;
		$('thead tr:first th', g.hDiv).each(function () {
			var thdiv = document.createElement('div');
			if ($(this).attr('abbr')) {
				$(this).click(function (e) {
					if (!$(this).hasClass('thOver')) return false;
					var obj = (e.target || e.srcElement);
					if (obj.href || obj.type) return true;
					g.changeSort(this);
				});
				if ($(this).attr('abbr') == p.sortname) {
					this.className = 'sorted';
					thdiv.className = 's' + p.sortorder;
				}
			}
			if (this.hidden) {
				$(this).hide();
			}
			if (!p.colmodel) {
				$(this).attr('axis', 'col' + ci++);
			}
			$(thdiv).css({
				textAlign: this.align,
				width: this.width + 'px'
			});
			thdiv.innerHTML = this.innerHTML;
			$(this).empty().append(thdiv).removeAttr('width').mousedown(function (e) {
				g.dragStart('colMove', e, this);
			}).hover(function () {
				if (!g.colresize && !$(this).hasClass('thMove') && !g.colCopy) {
					$(this).addClass('thOver');
				}
				if ($(this).attr('abbr') != p.sortname && !g.colCopy && !g.colresize && $(this).attr('abbr')) {
					$('div', this).addClass('s' + p.sortorder);
				} else if ($(this).attr('abbr') == p.sortname && !g.colCopy && !g.colresize && $(this).attr('abbr')) {
					var no = (p.sortorder == 'asc') ? 'desc' : 'asc';
					$('div', this).removeClass('s' + p.sortorder).addClass('s' + no);
				}
				if (g.colCopy) {
					var n = $('th', g.hDiv).index(this);
					if (n == g.dcoln) {
						return false;
					}
					if (n < g.dcoln) {
						$(this).append(g.cdropleft);
					} else {
						$(this).append(g.cdropright);
					}
					g.dcolt = n;
				} else if (!g.colresize) {
					var nv = $('th:visible', g.hDiv).index(this);
					var onl = parseInt($('div:eq(' + nv + ')', g.cDrag).css('left'));
					var nw = jQuery(g.nBtn).outerWidth();
					var nl = onl - nw + Math.floor(p.cgwidth / 2);
					$(g.nDiv).hide();
					$(g.nBtn).hide();
					$(g.nBtn).css({
						'left': nl,
						top: g.hDiv.offsetTop
					}).show();
					var ndw = parseInt($(g.nDiv).width());
					$(g.nDiv).css({
						top: g.bDiv.offsetTop
					});
					if ((nl + ndw) > $(g.gDiv).width()) {
						$(g.nDiv).css('left', onl - ndw + 1);
					} else {
						$(g.nDiv).css('left', nl);
					}
					if ($(this).hasClass('sorted')) {
						$(g.nBtn).addClass('srtd');
					} else {
						$(g.nBtn).removeClass('srtd');
					}
				}
			}, function () {
				$(this).removeClass('thOver');
				if ($(this).attr('abbr') != p.sortname) {
					$('div', this).removeClass('s' + p.sortorder);
				} else if ($(this).attr('abbr') == p.sortname) {
					var no = (p.sortorder == 'asc') ? 'desc' : 'asc';
					$('div', this).addClass('s' + p.sortorder).removeClass('s' + no);
				}
				if (g.colCopy) {
					$(g.cdropleft).remove();
					$(g.cdropright).remove();
					g.dcolt = null;
				}
			}); //wrap content
		});
		//set bDiv
		g.bDiv.className = 'bDiv';
		$(t).before(g.bDiv);
		$(g.bDiv).css({
			height: (p.height == 'auto') ? 'auto' : p.height + "px"
		}).scroll(function (e) {
			g.scroll()
		}).append(t);
		if (p.height == 'auto') {
			$('table', g.bDiv).addClass('autoht');
		}
		//add td & row properties
		g.addCellProp();
		g.addRowProp();
		//set cDrag
		var cdcol = $('thead tr:first th:first', g.hDiv).get(0);
		if (cdcol != null) {
			g.cDrag.className = 'cDrag';
			g.cdpad = 0;
			g.cdpad += (isNaN(parseInt($('div', cdcol).css('borderLeftWidth'))) ? 0 : parseInt($('div', cdcol).css('borderLeftWidth')));
			g.cdpad += (isNaN(parseInt($('div', cdcol).css('borderRightWidth'))) ? 0 : parseInt($('div', cdcol).css('borderRightWidth')));
			g.cdpad += (isNaN(parseInt($('div', cdcol).css('paddingLeft'))) ? 0 : parseInt($('div', cdcol).css('paddingLeft')));
			g.cdpad += (isNaN(parseInt($('div', cdcol).css('paddingRight'))) ? 0 : parseInt($('div', cdcol).css('paddingRight')));
			g.cdpad += (isNaN(parseInt($(cdcol).css('borderLeftWidth'))) ? 0 : parseInt($(cdcol).css('borderLeftWidth')));
			g.cdpad += (isNaN(parseInt($(cdcol).css('borderRightWidth'))) ? 0 : parseInt($(cdcol).css('borderRightWidth')));
			g.cdpad += (isNaN(parseInt($(cdcol).css('paddingLeft'))) ? 0 : parseInt($(cdcol).css('paddingLeft')));
			g.cdpad += (isNaN(parseInt($(cdcol).css('paddingRight'))) ? 0 : parseInt($(cdcol).css('paddingRight')));
			$(g.bDiv).before(g.cDrag);
			var cdheight = $(g.bDiv).height();
			var hdheight = $(g.hDiv).height();
			$(g.cDrag).css({
				top: -hdheight + 'px'
			});
			$('thead tr:first th', g.hDiv).each(function () {
				var cgDiv = document.createElement('div');
				$(g.cDrag).append(cgDiv);
				if (!p.cgwidth) {
					p.cgwidth = $(cgDiv).width();
				}
				$(cgDiv).css({
					height: cdheight + hdheight
				}).mousedown(function (e) {
					g.dragStart('colresize', e, this);
				});
				if ($.browser.msie && $.browser.version < 7.0) {
					g.fixHeight($(g.gDiv).height());
					$(cgDiv).hover(function () {
						g.fixHeight();
						$(this).addClass('dragging')
					}, function () {
						if (!g.colresize) $(this).removeClass('dragging')
					});
				}
			});
		}
		//add strip
		if (p.striped) {
			$('tbody tr:odd', g.bDiv).addClass('erow');
		}
		if (p.resizable && p.height != 'auto') {
			g.vDiv.className = 'vGrip';
			$(g.vDiv).mousedown(function (e) {
				g.dragStart('vresize', e)
			}).html('<span></span>');
			$(g.bDiv).after(g.vDiv);
		}
		if (p.resizable && p.width != 'auto' && !p.nohresize) {
			g.rDiv.className = 'hGrip';
			$(g.rDiv).mousedown(function (e) {
				g.dragStart('vresize', e, true);
			}).html('<span></span>').css('height', $(g.gDiv).height());
			if ($.browser.msie && $.browser.version < 7.0) {
				$(g.rDiv).hover(function () {
					$(this).addClass('hgOver');
				}, function () {
					$(this).removeClass('hgOver');
				});
			}
			$(g.gDiv).append(g.rDiv);
		}
		// add pager
		if (p.usepager) {
			g.pDiv.className = 'pDiv';
			g.pDiv.innerHTML = '<div class="pDiv2"></div>';
			$(g.bDiv).after(g.pDiv);
			var html = ' <div class="pGroup"> <div class="pFirst pButton"><span></span></div><div class="pPrev pButton"><span></span></div> </div> <div class="btnseparator"></div> <div class="pGroup"><span class="pcontrol">' + p.pagetext + ' <input type="text" size="4" value="1" /> ' + p.outof + ' <span> 1 </span></span></div> <div class="btnseparator"></div> <div class="pGroup"> <div class="pNext pButton"><span></span></div><div class="pLast pButton"><span></span></div> </div> <div class="btnseparator"></div> <div class="pGroup"> <div class="pReload pButton"><span></span></div> </div> <div class="btnseparator"></div> <div class="pGroup"><span class="pPageStat"></span></div>';
			$('div', g.pDiv).html(html);
			$('.pReload', g.pDiv).click(function () {
				g.populate()
			});
			$('.pFirst', g.pDiv).click(function () {
				g.changePage('first')
			});
			$('.pPrev', g.pDiv).click(function () {
				g.changePage('prev')
			});
			$('.pNext', g.pDiv).click(function () {
				g.changePage('next')
			});
			$('.pLast', g.pDiv).click(function () {
				g.changePage('last')
			});
			$('.pcontrol input', g.pDiv).keydown(function (e) {
				if (e.keyCode == 13) g.changePage('input')
			});
			if ($.browser.msie && $.browser.version < 7) $('.pButton', g.pDiv).hover(function () {
				$(this).addClass('pBtnOver');
			}, function () {
				$(this).removeClass('pBtnOver');
			});
			if (p.useRp) {
				var opt = '',
					sel = '';
				for (var nx = 0; nx < p.rpOptions.length; nx++) {
					if (p.rp == p.rpOptions[nx]) sel = 'selected="selected"';
					else sel = '';
					opt += "<option value='" + p.rpOptions[nx] + "' " + sel + " >" + p.rpOptions[nx] + "&nbsp;&nbsp;</option>";
				}
				$('.pDiv2', g.pDiv).prepend("<div class='pGroup'><select name='rp'>" + opt + "</select></div> <div class='btnseparator'></div>");
				$('select', g.pDiv).change(function () {
					if (p.onRpChange) {
						p.onRpChange(+this.value);
					} else {
						p.newp = 1;
						p.rp = +this.value;
						g.populate();
					}
				});
			}	

			//add search and advanced search button
			if (p.searchitems) {
				$('.pDiv2', g.pDiv).prepend("<div class='pGroup'> <div class='pAdvSearch pButton'></div></div><div class='pGroup'><span class='pcontrol'>�߼���ѯ</span></div>");
				$('.pDiv2', g.pDiv).prepend("<div class='pGroup'> <div class='pSearch pButton'></div></div><div class='pGroup'><span class='pcontrol'>һ���ѯ</span></div>");
				$('.pAdvSearch', g.pDiv).click(function () {
					$(g.s1Div).slideToggle('fast', function() {
						$('.s1Div:visible input:first', g.gDiv).trigger('focus');
					})
				})
				$('.pSearch', g.pDiv).click(function () {
					$(g.sDiv).slideToggle('fast', function () {
						$('.sDiv:visible input:first', g.gDiv).trigger('focus');
					});
				});
				g.s1Div.className = 's1Div';
				$(g.s1Div).append("<div class='s1Div2'>" +
						"<form method='post'>"+
						"&nbsp;����&nbsp;"+"<input type='text' value='' size='10' name='cname' class='qsbox' /> "+
						"&nbsp;�Ա�&nbsp;"+"<select name='csex'><option value='' selected='selected'>����</option><option value='��'>��</option><option value='Ů'>Ů</option></select>" +
						"&nbsp;����&nbsp;"+"<input type='text' value='' size='11' name='cage1' class='qsbox' onClick='calendar_date()'/>--"+
						"<input type='text' value='' size='11' name='cage2' class='qsbox' onClick='calendar_date()'/>"+
						"&nbsp;ְλ&nbsp;"+"<input type='text' value='' size='10' name='ctitle' class='qsbox' /><br><br>"+
						"&nbsp;ʡ��&nbsp;"+"<select name='cprovince'><option value='0' selected='selected'>����</option>"+
						"<option value='1'>����</option><option value='2'>�Ϻ�</option><option value='3'>���</option><option value='4'>����</option><option value='5'>�ӱ�</option>"+
					    "<option value='6'>ɽ��</option><option value='7'>���ɹ�</option><option value='8'>����</option><option value='9'>����</option><option value='10'>������</option>"+
					    "<option value='11'>����</option><option value='12'>�㽭</option><option value='13'>����</option><option value='14'>����</option><option value='15'>����</option>"+
					    "<option value='16'>ɽ��</option><option value='17'>����</option><option value='18'>����</option><option value='19'>����</option><option value='20'>�㶫</option>"+
					    "<option value='21'>����</option><option value='22'>����</option><option value='23'>�Ĵ�</option><option value='24'>����</option><option value='25'>����</option>"+
					    "<option value='26'>����</option><option value='27'>����</option><option value='28'>����</option><option value='29'>����</option><option value='30'>�ຣ</option>"+
					    "<option value='31'>�½�</option><option value='32'>���</option><option value='33'>����</option><option value='34'>̨��</option> </select>"+
						"&nbsp;����&nbsp;"+"<select name='ccity'><option selected='selected'></option></select>"+
						"&nbsp;�ʱ�&nbsp;"+"<input type='text' value='' size='10' name='cpost' class='qsbox' />" +
						"&nbsp;�̻�&nbsp;"+"<input type='text' value='' size='10' name='cfix' class='qsbox' /><br><br>"+
						"&nbsp;���֤&nbsp;"+"<input type='text' value='' size='20' name='cid' class='qsbox' />"+
						"&nbsp;��ַ&nbsp;"+"<input type='text' value='' size='30' name='caddr' class='qsbox' />"+
						"&nbsp;��˾&nbsp;"+"<input type='text' value='' size='20' name='ccompany' class='qsbox' /><br><br>"+
						"<div class='cusertype'>&nbsp;�����߼�&nbsp;<input type='radio' name='clogic' checked='checked' value='and' class='qsbox'/>&nbsp;����&nbsp;<input type='radio' name='clogic' value='or' class='qsbox'/>&nbsp;�ϼ�&nbsp;</div><br>"+						
						"&nbsp;������Դ&nbsp;"+"<input type='text' value='' size='10' name='csource' class='qsbox' />"+
						"&nbsp;��ע&nbsp;"+"<input type='text' value='' size='30' name='cother' class='qsbox' /><br><br>"+
						"<input type='button' name='advsearchbtn' value='�߼���ѯ'> &nbsp;&nbsp;<input type=reset value='���'> &nbsp;&nbsp;<input type='button' name='exphonebtn' value='�����ֻ�����'>&nbsp;&nbsp;<input type='button' name='exmailbtn' value='���������ַ'><br><br>"+
						"&nbsp;ҵ����ʱ��&nbsp;"+"<input type='text' value='' size='20' name='cservtime' class='qsbox' onClick='calendar_date()'/>&nbsp;&nbsp;<image name='servid' src='images/find.png' style='cursor: hand' alt='�鿴ҵ������' />"+
						"&nbsp;&nbsp;<select name='cservid' class='cserviceopt'></select><br><br>" +
		                "<input type='button' name='markbtn' value='���'>"+
		                "</form>"+
				        "</div>");
				
				$.ajax({
					type: p.method,
					url: p.url,
					data: 'action=getusertype',
					dataType:'html',
					success: function (data) {						
						$('.cusertype', g.s1Div).prepend(data);
						$('.cusertype', g.s1Div).prepend("&nbsp;�������&nbsp;");
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
						try {
							if (p.onError) p.onError(XMLHttpRequest, textStatus, errorThrown);
						} catch (e) {}
					}
				});
				
				$('select[name=cprovince]', g.s1Div).click(function (){
					var x = [35];
					x[0]="";
					x[1]="����,����,����,����,����,����,��̨,ʯ��ɽ,����,��ͷ��,��ɽ,ͨ��,˳��,��ƽ,����,ƽ��,����,����,����" ;
					x[2]="�Ϻ�,����,¬��,���,����,����,����,բ��,���,����,����,��ɽ,�ζ�,�ֶ�,��ɽ,�ɽ�,����,�ϻ�,����,����" ;
					x[3]="���,��ƽ,����,�Ӷ�,����,����,����,�Ͽ�,����,�ӱ�,����,����,����,����,���,����,����,����,����,����ׯ";
					x[4]="����,����,����,����,��ɿ�,����,ɳƺ��,������,�ϰ�,����,��ʢ,˫��,�山,����,ǭ��,����,�뽭,����,ͭ��,����,�ٲ�,��ɽ,��ƽ,�ǿ�,�ᶼ,�潭,��¡,����,����,����,���,��ɽ,��Ϫ,ʯ��,��ɽ,����,��ˮ,����,�ϴ�,����,�ϴ�";
					x[5]="ʯ��ׯ,����,��̨,����,�żҿ�,�е�,�ȷ�,��ɽ,�ػʵ�,����,��ˮ"; 
					x[6]="̫ԭ,��ͬ,��Ȫ,����,����,˷��,����,����,����,�ٷ�,�˳�"; 
					x[7]="���ͺ���,��ͷ,�ں�,���,���ױ�����,��������,����ľ��,�˰���,�����첼��,���ֹ�����,�����׶���,��������" ;
					x[8]="����,����,��ɽ,��˳,��Ϫ,����,����,Ӫ��,����,����,�̽�,����,����,��«��" ;
					x[9]="����,����,��ƽ,��Դ,ͨ��,��ɽ,��ԭ,�׳�,�ӱ�" ;
					x[10]="������,�������,ĵ����,��ľ˹,����,�绯,�׸�,����,�ں�,˫Ѽɽ,����,��̨��,���˰���" ;
					x[11]="�Ͼ�,��,����,��ͨ,����,�γ�,����,���Ƹ�,����,����,��Ǩ,̩��,����" ;
					x[12]="����,����,����,����,����,����,��,����,��ɽ,̨��,��ˮ" ;
					x[13]="�Ϸ�,�ߺ�,����,��ɽ,����,ͭ��,����,��ɽ,����,����,����,����,����,����,����,����,����" ;
					x[14]="����,����,����,����,Ȫ��,����,��ƽ,����,����" ;
					x[15]="�ϲ���,������,�Ž�,ӥ̶,Ƽ��,����,����,����,�˴�,����,����" ;
					x[16]="����,�ൺ,�Ͳ�,��ׯ,��Ӫ,��̨,Ϋ��,����,̩��,����,����,����,����,����,�ĳ�,����,����,����" ;
					x[17]="֣��,����,����,ƽ��ɽ,����,�ױ�,����,����,���,���,���,����Ͽ,����,����,����,�ܿ�,פ���,��Դ" ;
					x[18]="�人,�˲�,����,�差,��ʯ,����,�Ƹ�,ʮ��,��ʩ,Ǳ��,����,����,����,����,Т��,����" ;
					x[19]="��ɳ,����,����,��̶,����,����,����,����,¦��,����,����,����,����,�żҽ�" ;
					x[20]="����,����,�麣,��ͷ,��ݸ,��ɽ,��ɽ,�ع�,����,տ��,ï��,����,����,÷��,��β,��Դ,����,��Զ,����,����,�Ƹ�" ;
					x[21]="����,����,����,����,����,���Ǹ�,����,���,����,��������,���ݵ���,����,��ɫ,�ӳ�" ;
					x[22]="����,����" ;
					x[23]="�ɶ�,����,����,�Թ�,��֦��,��Ԫ,�ڽ�,��ɽ,�ϳ�,�˱�,�㰲,�ﴨ,�Ű�,üɽ,����,��ɽ,����" ;
					x[24]="����,����ˮ,����,��˳,ͭ��,ǭ����,�Ͻ�,ǭ����,ǭ��" ;
					x[25]="����,����,����,��Ϫ,��ͨ,����,���,��ɽ,˼é,��˫����,��ɽ,�º�,����,ŭ��,����,�ٲ�" ;
					x[26]="����,�տ���,ɽ��,��֥,����,����,����" ;
					x[27]="����,����,����,ͭ��,μ��,�Ӱ�,����,����,����,����" ;
					x[28]="����,������,���,����,��ˮ,��Ȫ,��Ҵ,����,����,¤��,ƽ��,����,����,����" ;
					x[29]="����,ʯ��ɽ,����,��ԭ" ;
					x[30]="����,����,����,����,����,����,����,����" ;
					x[31]="��³ľ��,ʯ����,��������,����,��������,����,�������տ¶�����,�� ������,��³��,����,��ʲ,����,������" ;
					x[32]="���," ;
					x[33]="����," ;
					x[34]="̨��,����,̨��,̨��,����,��Ͷ,����,����,�û�,����,����,����,��԰,����,��¡,̨��,����,����,���" ;
					var citys=x[$('select[name=cprovince]', g.s1Div).val()].split(",");
					$('select[name=ccity] option', g.s1Div).each(function(){
						$(this).remove();
					});
					for(var i=0;i<citys.length;i++)
					{						
						$('select[name=ccity]', g.s1Div).append("<option value='"+citys[i]+"'>" +citys[i]+ "</option>");
					}
					
				});
				
				$('image[name=servid]', g.s1Div).click(function (e) {
						$.ajax({
							type: p.method,
							url: p.url,
							data: 'action=getserviceid&servicedate='+$('input[name=cservtime]', g.s1Div).val(),
							dataType:'html',
							success: function (data) {
								$('.cserviceopt option', g.s1Div).each(function(){
									$(this).remove();
								});
								$('.cserviceopt', g.s1Div).append(data);
							},
							error: function (XMLHttpRequest, textStatus, errorThrown) {
								try {
									if (p.onError) p.onError(XMLHttpRequest, textStatus, errorThrown);
								} catch (e) {}
							}
						});
				});
				
				$('input[name=advsearchbtn]', g.s1Div).click(function (){
					p.cnamevalue = $('input[name=cname]', g.s1Div).val();
					p.csexvalue = $('select[name=csex]', g.s1Div).val();
					p.cage1value = $('input[name=cage1]', g.s1Div).val();
					p.cage2value = $('input[name=cage2]', g.s1Div).val();
					p.ctitlevalue = $('input[name=ctitle]', g.s1Div).val();
					p.cprovincevalue = province[$('select[name=cprovince]', g.s1Div).val()];
				    p.ccityvalue = $('select[name=ccity]', g.s1Div).val();
				    p.cpostvalue  = $('input[name=cpost]', g.s1Div).val();
				    p.cfixvalue = $('input[name=cfix]', g.s1Div).val();
				    p.cidvalue = $('input[name=cid]', g.s1Div).val();
				    p.caddrvalue= $('input[name=caddr]', g.s1Div).val();
				    p.ccompanyvalue= $('input[name=ccompany]', g.s1Div).val();
				    p.ctypevalue=0;
				    $('input[name=ctype]', g.s1Div).each(function(){
				    	if ($(this).attr("checked") == "checked") {
				    	    p.ctypevalue+=parseInt($(this).val());
				    	}
				    });
				    $('input[name=clogic]', g.s1Div).each(function(){
				    	if ($(this).attr("checked") == "checked") {
				    		p.ctypelogic=$(this).val();
				    	}
				    });
				    
				    p.csourcevalue = $('input[name=csource]', g.s1Div).val();
				    p.cothervalue= $('input[name=cother]', g.s1Div).val();
					p.newp = 1;
					p.searchtype="advance";
					g.populate();
				});
				
				$('input[name=markbtn]', g.s1Div).click(function (){
					if($('select[name=cservid]', g.s1Div).val() == null)
					{
						alert("����ʧ��:ҵ������Ϊ�գ�");
						return;
					}
					
					if(confirm("�Ƿ�ȷ�϶Ե�ǰĿ���ѯ�û�����ҵ���ǣ�"+$('select[name=cservid]', g.s1Div).val()+"?"))
					{
					$('input[name=markbtn]', g.s1Div).attr('disabled', true);
					p.cnamevalue = $('input[name=cname]', g.s1Div).val();
					p.csexvalue = $('select[name=csex]', g.s1Div).val();
					p.cage1value = $('input[name=cage1]', g.s1Div).val();
					p.cage2value = $('input[name=cage2]', g.s1Div).val();
					p.ctitlevalue = $('input[name=ctitle]', g.s1Div).val();
					p.cprovincevalue = province[$('select[name=cprovince]', g.s1Div).val()];
				    p.ccityvalue = $('select[name=ccity]', g.s1Div).val();
				    p.cpostvalue  = $('input[name=cpost]', g.s1Div).val();
				    p.cfixvalue = $('input[name=cfix]', g.s1Div).val();
				    p.cidvalue = $('input[name=cid]', g.s1Div).val();
				    p.caddrvalue= $('input[name=caddr]', g.s1Div).val();
				    p.ccompanyvalue= $('input[name=ccompany]', g.s1Div).val();
				    p.ctypevalue=0;
				    $('input[name=ctype]', g.s1Div).each(function(){
				    	if ($(this).attr("checked") == "checked") {
				    	    p.ctypevalue+=parseInt($(this).val());
				    	}
				    });
				    $('input[name=clogic]', g.s1Div).each(function(){
				    	if ($(this).attr("checked") == "checked") {
				    	    p.ctypelogic=$(this).val();
				    	}
				    });
				    alert(p.ctypelogic);
				    
				    p.csourcevalue = $('input[name=csource]', g.s1Div).val();
				    p.cothervalue= $('input[name=cother]', g.s1Div).val();
					$.ajax({
						type: p.method,
						url: p.url,
						data: 'action=mark&serviceid='+$('select[name=cservid]', g.s1Div).val()+g.constructfilter(),
						dataType:'html',
						success: function (data) {
							$('input[name=markbtn]', g.s1Div).attr('disabled', false);
							alert("�����ɹ�");
						},
						error: function (XMLHttpRequest, textStatus, errorThrown) {
							try {
								$('input[name=markbtn]', g.s1Div).attr('disabled', false);
								if (p.onError) p.onError(XMLHttpRequest, textStatus, errorThrown);
							} catch (e) {}
						}
					});
					}
				});
				
				$('input[name=exphonebtn]', g.s1Div).click(function (){
					p.cnamevalue = $('input[name=cname]', g.s1Div).val();
					p.csexvalue = $('select[name=csex]', g.s1Div).val();
					p.cage1value = $('input[name=cage1]', g.s1Div).val();
					p.cage2value = $('input[name=cage2]', g.s1Div).val();
					p.ctitlevalue = $('input[name=ctitle]', g.s1Div).val();
					p.cprovincevalue = province[$('select[name=cprovince]', g.s1Div).val()];
				    p.ccityvalue = $('select[name=ccity]', g.s1Div).val();
				    p.cpostvalue  = $('input[name=cpost]', g.s1Div).val();
				    p.cfixvalue = $('input[name=cfix]', g.s1Div).val();
				    p.cidvalue = $('input[name=cid]', g.s1Div).val();
				    p.caddrvalue= $('input[name=caddr]', g.s1Div).val();
				    p.ccompanyvalue= $('input[name=ccompany]', g.s1Div).val();
				    p.ctypevalue=0;
				    $('input[name=ctype]', g.s1Div).each(function(){
				    	if ($(this).attr("checked") == "checked") {
				    		p.ctypevalue+=parseInt($(this).val());
				    	}
				    });
				    $('input[name=clogic]', g.s1Div).each(function(){
				    	if ($(this).attr("checked") == "checked") {	
				    		p.ctypelogic=$(this).val();				    	
				    	}
				    });
				    
				    p.csourcevalue = $('input[name=csource]', g.s1Div).val();
				    p.cothervalue= $('input[name=cother]', g.s1Div).val();
					window.location.href="CustomerServlet?action=exmobile"+g.constructfilter();
				});
				
				$('input[name=exmailbtn]', g.s1Div).click(function (){
					p.cnamevalue = $('input[name=cname]', g.s1Div).val();
					p.csexvalue = $('select[name=csex]', g.s1Div).val();
					p.cage1value = $('input[name=cage1]', g.s1Div).val();
					p.cage2value = $('input[name=cage2]', g.s1Div).val();
					p.ctitlevalue = $('input[name=ctitle]', g.s1Div).val();
					p.cprovincevalue = province[$('select[name=cprovince]', g.s1Div).val()];
				    p.ccityvalue = $('select[name=ccity]', g.s1Div).val();
				    p.cpostvalue  = $('input[name=cpost]', g.s1Div).val();
				    p.cfixvalue = $('input[name=cfix]', g.s1Div).val();
				    p.cidvalue = $('input[name=cid]', g.s1Div).val();
				    p.caddrvalue= $('input[name=caddr]', g.s1Div).val();
				    p.ccompanyvalue= $('input[name=ccompany]', g.s1Div).val();
				    p.ctypevalue=0;
				    $('input[name=ctype]', g.s1Div).each(function(){
				    	if ($(this).attr("checked") == "checked") {
				    	    p.ctypevalue+=parseInt($(this).val());
				    	}
				    });
				    $('input[name=clogic]', g.s1Div).each(function(){
				    	if ($(this).attr("checked") == "checked") {
				    	    p.ctypelogic=$(this).val();
				    	}
				    });
				    
				    p.csourcevalue = $('input[name=csource]', g.s1Div).val();
				    p.cothervalue= $('input[name=cother]', g.s1Div).val();
					window.location.href="CustomerServlet?action=exmail"+g.constructfilter();
				});
				        
				$(g.bDiv).after(g.s1Div);
				
				//add search box
				g.sDiv.className = 'sDiv';
				var sitems = p.searchitems;
				var sopt = '', sel = '';
				for (var s = 0; s < sitems.length; s++) {
					if (p.qtype == '' && sitems[s].isdefault == true) {
						p.qtype = sitems[s].name;
						sel = 'selected="selected"';
					} else {
						sel = '';
					}
					sopt += "<option value='" + sitems[s].name + "' " + sel + " >" + sitems[s].display + "&nbsp;&nbsp;</option>";
				}
				if (p.qtype == '') {
					p.qtype = sitems[0].name;
				}
				$(g.sDiv).append("<div class='sDiv2'>" + p.findtext + 
						" <input type='text' value='" + p.query +"' size='30' name='q' class='qsbox' /> "+
						" <select name='qtype'>" + sopt + "</select></div>");
				//Split into separate selectors because of bug in jQuery 1.3.2
				$('input[name=q]', g.sDiv).keydown(function (e) {
					if (e.keyCode == 13) {
						g.doSearch();
					}
				});
				$('select[name=qtype]', g.sDiv).keydown(function (e) {
					if (e.keyCode == 13) {
						g.doSearch();
					}
				});
				$('input[value=Clear]', g.sDiv).click(function () {
					$('input[name=q]', g.sDiv).val('');
					p.query = '';
					g.doSearch();
				});
				$(g.bDiv).after(g.sDiv);
			}
		}
		$(g.pDiv, g.sDiv).append("<div style='clear:both'></div>");
		
		// add title
		if (p.title) {
			g.mDiv.className = 'mDiv';
			g.mDiv.innerHTML =  "<table border='0' cellpadding='0' align='left' bgcolor='gray' width=100%>"+
			                    "<tr align='center'><td width=5%><input padding=10 name='advsearch' type='button' value='ȷ�ϲ�ѯ'></input>"+
                                "<td width=5%><input name='reset' type='reset' value='�������'></input></td>"+
                                "<td width=5%><input name='exphone' type='button' value='�����ֻ���'></input></td>"+
                                "<td width=5%><input name='exmail' type='button' value='��������'></input></td>"+
                                "<td width=5%><input name='servquery' type='button' value='ҵ���ѯ'></input></td>"+
                                "<td width=5%><input name='batchedit' type='button' value='�����޸�'></input></td>"+
                                "<td width=5%><input name='batchdel' type='button' value='����ɾ��'></input></td>" +
                                "<td width=65% align='left'><input type='checkbox' name='qall' value='1' />ȫ��ѡ��</td></tr>"+
			                    "</table>"+
				                "<table border='0' cellpadding='4' align='center'>"+			                    
                                "<tr><td>�ͻ�ID</td><td><input type='text' value='' size='10' name='qid' class='qsbox' /></td>"+
                                "<td>����</td><td><input type='text' value='' size='5' name='qname' class='qsbox' /></td>"+
                                "<td>�Ա�</td><td><select name='qsex'><option value='' selected='selected'>����</option><option value='��'>��</option><option value='Ů'>Ů</option></select></td>"+
                                "<td>����</td><td><input type='text' value='' size='10' name='qage1' class='qsbox' onClick='calendar_date()'/>--<input type='text' value='' size='10' name='qage2' class='qsbox' onClick='calendar_date()'/></td>"+
                                "<td>��˾</td><td><input type='text' value='' size='10' name='qcompany' class='qsbox' /></td>"+
                                "<td>ְλ</td><td><input type='text' value='' size='10' name='qtitle' class='qsbox' /></td>"+
                                "<td>ʡ��</td><td><select name='qprovince'><option value='0' selected='selected'>����</option>"+
						        "<option value='1'>����</option><option value='2'>�Ϻ�</option><option value='3'>���</option><option value='4'>����</option><option value='5'>�ӱ�</option>"+
					            "<option value='6'>ɽ��</option><option value='7'>���ɹ�</option><option value='8'>����</option><option value='9'>����</option><option value='10'>������</option>"+
					            "<option value='11'>����</option><option value='12'>�㽭</option><option value='13'>����</option><option value='14'>����</option><option value='15'>����</option>"+
					            "<option value='16'>ɽ��</option><option value='17'>����</option><option value='18'>����</option><option value='19'>����</option><option value='20'>�㶫</option>"+
					            "<option value='21'>����</option><option value='22'>����</option><option value='23'>�Ĵ�</option><option value='24'>����</option><option value='25'>����</option>"+
					            "<option value='26'>����</option><option value='27'>����</option><option value='28'>����</option><option value='29'>����</option><option value='30'>�ຣ</option>"+
					            "<option value='31'>�½�</option><option value='32'>���</option><option value='33'>����</option><option value='34'>̨��</option> </select></td>"+
					            "<td>����</td><td><select name='qcity' style= 'width:100px;'><option value='' selected='selected'>����</option></select></td>"+
					            "<td>�ʱ�</td><td><input type='text' value='' size='10' name='qpost' class='qsbox' /></td></tr>"+
					            "<tr><td>���֤��</td><td><input type='text' value='' size='10' name='qidcard' class='qsbox' /></td>" +
                                "<td>�ֻ�</td><td><input type='text' value='' size='11' name='qmobile' class='qsbox' /></td>"+
                                "<td>�̻�</td><td><input type='text' value='' size='12' name='qfix' class='qsbox' /></td>"+
                                "<td>��ַ</td><td><input type='text' value='' size='20' name='qaddr' class='qsbox' /></td>" +
                                "<td>����</td><td><input type='text' value='' size='10' name='qmail' class='qsbox' /></td>"+
                                "<td>����Դ</td><td><input type='text' value='' size='10' name='qsource' class='qsbox' /></td>"+
                                "<td>�ͻ���ע</td><td><input type='text' value='' size='10' name='qother' class='qsbox' /></td>"+
                                "<td>ҵ��״̬</td><td><select name='qservstate'><option value='' selected='selected'>����</option><option value='0'>δ����</option><option value='1'>����</option><option value='2'>��ʧ��</option><option value='3'>����Ӧ</option><option value='4'>�Ѷ���</option></select></td></tr>"+
                                "<tr><td colspan=18><div class='qusertype' height='5px'>�����߼�<input type='radio' name='qlogicvalue' checked='checked' value='and' class='qsbox'/>����<input type='radio' name='qlogicvalue' value='or' class='qsbox'/>�ϼ�</div></td></tr>"+
                                "</table>";
			
			$('select[name=qprovince]', g.mDiv).click(function (){
				var x = [35];
				x[0]="";
				x[1]="����,����,����,����,����,����,��̨,ʯ��ɽ,����,��ͷ��,��ɽ,ͨ��,˳��,��ƽ,����,ƽ��,����,����,����" ;
				x[2]="�Ϻ�,����,¬��,���,����,����,����,բ��,���,����,����,��ɽ,�ζ�,�ֶ�,��ɽ,�ɽ�,����,�ϻ�,����,����" ;
				x[3]="���,��ƽ,����,�Ӷ�,����,����,����,�Ͽ�,����,�ӱ�,����,����,����,����,���,����,����,����,����,����ׯ";
				x[4]="����,����,����,����,��ɿ�,����,ɳƺ��,������,�ϰ�,����,��ʢ,˫��,�山,����,ǭ��,����,�뽭,����,ͭ��,����,�ٲ�,��ɽ,��ƽ,�ǿ�,�ᶼ,�潭,��¡,����,����,����,���,��ɽ,��Ϫ,ʯ��,��ɽ,����,��ˮ,����,�ϴ�,����,�ϴ�";
				x[5]="ʯ��ׯ,����,��̨,����,�żҿ�,�е�,�ȷ�,��ɽ,�ػʵ�,����,��ˮ"; 
				x[6]="̫ԭ,��ͬ,��Ȫ,����,����,˷��,����,����,����,�ٷ�,�˳�"; 
				x[7]="���ͺ���,��ͷ,�ں�,���,���ױ�����,��������,����ľ��,�˰���,�����첼��,���ֹ�����,�����׶���,��������" ;
				x[8]="����,����,��ɽ,��˳,��Ϫ,����,����,Ӫ��,����,����,�̽�,����,����,��«��" ;
				x[9]="����,����,��ƽ,��Դ,ͨ��,��ɽ,��ԭ,�׳�,�ӱ�" ;
				x[10]="������,�������,ĵ����,��ľ˹,����,�绯,�׸�,����,�ں�,˫Ѽɽ,����,��̨��,���˰���" ;
				x[11]="�Ͼ�,��,����,��ͨ,����,�γ�,����,���Ƹ�,����,����,��Ǩ,̩��,����" ;
				x[12]="����,����,����,����,����,����,��,����,��ɽ,̨��,��ˮ" ;
				x[13]="�Ϸ�,�ߺ�,����,��ɽ,����,ͭ��,����,��ɽ,����,����,����,����,����,����,����,����,����" ;
				x[14]="����,����,����,����,Ȫ��,����,��ƽ,����,����" ;
				x[15]="�ϲ���,������,�Ž�,ӥ̶,Ƽ��,����,����,����,�˴�,����,����" ;
				x[16]="����,�ൺ,�Ͳ�,��ׯ,��Ӫ,��̨,Ϋ��,����,̩��,����,����,����,����,����,�ĳ�,����,����,����" ;
				x[17]="֣��,����,����,ƽ��ɽ,����,�ױ�,����,����,���,���,���,����Ͽ,����,����,����,�ܿ�,פ���,��Դ" ;
				x[18]="�人,�˲�,����,�差,��ʯ,����,�Ƹ�,ʮ��,��ʩ,Ǳ��,����,����,����,����,Т��,����" ;
				x[19]="��ɳ,����,����,��̶,����,����,����,����,¦��,����,����,����,����,�żҽ�" ;
				x[20]="����,����,�麣,��ͷ,��ݸ,��ɽ,��ɽ,�ع�,����,տ��,ï��,����,����,÷��,��β,��Դ,����,��Զ,����,����,�Ƹ�" ;
				x[21]="����,����,����,����,����,���Ǹ�,����,���,����,��������,���ݵ���,����,��ɫ,�ӳ�" ;
				x[22]="����,����" ;
				x[23]="�ɶ�,����,����,�Թ�,��֦��,��Ԫ,�ڽ�,��ɽ,�ϳ�,�˱�,�㰲,�ﴨ,�Ű�,üɽ,����,��ɽ,����" ;
				x[24]="����,����ˮ,����,��˳,ͭ��,ǭ����,�Ͻ�,ǭ����,ǭ��" ;
				x[25]="����,����,����,��Ϫ,��ͨ,����,���,��ɽ,˼é,��˫����,��ɽ,�º�,����,ŭ��,����,�ٲ�" ;
				x[26]="����,�տ���,ɽ��,��֥,����,����,����" ;
				x[27]="����,����,����,ͭ��,μ��,�Ӱ�,����,����,����,����" ;
				x[28]="����,������,���,����,��ˮ,��Ȫ,��Ҵ,����,����,¤��,ƽ��,����,����,����" ;
				x[29]="����,ʯ��ɽ,����,��ԭ" ;
				x[30]="����,����,����,����,����,����,����,����" ;
				x[31]="��³ľ��,ʯ����,��������,����,��������,����,�������տ¶�����,�� ������,��³��,����,��ʲ,����,������" ;
				x[32]="���," ;
				x[33]="����," ;
				x[34]="̨��,����,̨��,̨��,����,��Ͷ,����,����,�û�,����,����,����,��԰,����,��¡,̨��,����,����,���" ;
				var citys=x[$('select[name=qprovince]', g.mDiv).val()].split(",");
				$('select[name=qcity] option', g.mDiv).each(function(){
					$(this).remove();
				});
				$('select[name=qcity]', g.mDiv).append("<option value='"+""+"'>" +"����"+ "</option>");
				for(var i=0;i<citys.length;i++)
				{						
					$('select[name=qcity]', g.mDiv).append("<option value='"+citys[i]+"'>" +citys[i]+ "</option>");
				}
				
			});
			
			$.ajax({
				type: p.method,
				url: p.url,
				data: 'action=getusertype',
				dataType:'html',
				success: function (data) {						
					$('.qusertype', g.mDiv).prepend(data);
					$('.qusertype', g.mDiv).prepend("�������");
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {
					try {
						if (p.onError) p.onError(XMLHttpRequest, textStatus, errorThrown);
					} catch (e) {}
				}
			});
			
			$('input[name=reset]', g.mDiv).click(function (){
				$('input[name=qid]', g.mDiv).val("");
				$('input[name=qname]', g.mDiv).val("");
				$('select[name=qsex] option').eq(0).attr('selected', 'true');
				$('input[name=qage1]', g.mDiv).val("");
				$('input[name=qage2]', g.mDiv).val("");
				$('input[name=qcompany]', g.mDiv).val("");
				$('input[name=qtitle]', g.mDiv).val("");
				$('select[name=qprovince] option').eq(0).attr('selected', 'true');
				$('select[name=qcity] option').eq(0).attr('selected', 'true');
				$('input[name=qpost]', g.mDiv).val("");
				$('input[name=qidcard]', g.mDiv).val("");
				$('input[name=qmobile]', g.mDiv).val("");
				$('input[name=qfix]', g.mDiv).val("");
				$('input[name=qaddr]', g.mDiv).val("");
				$('input[name=qmail]', g.mDiv).val("");
				$('input[name=qsource]', g.mDiv).val("");
				$('input[name=qother]', g.mDiv).val("");
				$('select[name=qservstat] option').eq(0).attr('selected', 'true');
				$('input[name=qlogicvalue][value=and]').attr("checked",true);
				$('input[name=qtypevalue]').attr('checked', false);
			});
			
			$('input[name=servquery]', g.mDiv).click(function (){
				if($(".trSelected").length==1)
			  	{
					var id = $('.trSelected').find("td").eq(0).text();
					window.open('customer_service.jsp?id='+id);
			  	}
			});
			
			$('input[name=batchedit]', g.mDiv).click(function (){
				if($(".trSelected").length>0)
			  	{
					var id='';
					$(".trSelected", g.bDiv).each(function () {
						if(id != '')
							id += '_';
					    id += $(this).find("td").eq(0).text();
					});
					window.open('customer_edit.jsp?id='+id);
			  	}
			  	else
			  	{
			  	  alert("��ѡ��Ҫ�޸ĵļ�¼");
			  	}
			});
			
			$('input[name=batchdel]', g.mDiv).click(function (){
				if($(".trSelected").length>0)
			  	{
					if(confirm('�Ƿ�ɾ���� ' + $('.trSelected',g.bDiv).length + ' ����¼��?'))
				  	{
					var id='';
					$(".trSelected", g.bDiv).each(function () {
						if(id != '')
							id += '_';
					    id += $(this).find("td").eq(0).text();
					});
					
					$.ajax({
			        	type: "POST",
			        	url: "CustomerServlet?action=batchdel",
			            data: "id="+id,
			            dataType:"text",
			            success: function(msg){
			            	$("#flex1").flexReload();
			              },
			  	        error: function(msg){
			  	          alert(msg);
			  	        }
			        });
				  	}
			  	}
			  	else
			  	{
			  	  alert("��ѡ��Ҫɾ���ļ�¼");
			  	}
			});
			
			$('input[name=qall]', g.mDiv).click(function(){
				if ($(this).attr("checked") == "checked") {
					$('tbody tr', g.bDiv).each(function () {
						$(this).addClass('trSelected');
					});
				}
				else
				{
					$('tbody tr', g.bDiv).each(function () {
						$(this).removeClass('trSelected');
					});
				}
			});
			
			$('input[name=advsearch]', g.mDiv).click(function (){
				p.cnamevalue = $('input[name=qname]',g.mDiv).val();
				p.csexvalue = $('select[name=qsex]', g.mDiv).val();
				p.cage1value = $('input[name=qage1]',g.mDiv).val();
				p.cage2value = $('input[name=qage2]', g.mDiv).val();
				p.cidcardvalue = $('input[name=qidcard]', g.mDiv).val();
				p.ctitlevalue = $('input[name=qtitle]', g.mDiv).val();
				p.cprovincevalue = province[$('select[name=qprovince]',g.mDiv).val()];
			    p.ccityvalue = $('select[name=qcity]',g.mDiv).val();
			    p.cpostvalue  = $('input[name=qpost]',g.mDiv).val();
			    p.cmobilevalue = $('input[name=qmobile]',g.mDiv).val();
			    p.cfixvalue = $('input[name=qfix]',g.mDiv).val();
			    p.cmailvalue = $('input[name=qmail]',g.mDiv).val();
			    p.cidvalue = $('input[name=qid]', g.mDiv).val();
			    p.caddrvalue= $('input[name=qaddr]',g.mDiv).val();
			    p.ccompanyvalue= $('input[name=qcompany]', g.mDiv).val();
			    p.ctypevalue=0;
			    $('input[name=qtypevalue]',g.mDiv).each(function(){
			    	if ($(this).attr("checked") == "checked") {
			    	    p.ctypevalue+=parseInt($(this).val());
			    	}
			    });
			    $('input[name=qlogicvalue]', g.mDiv).each(function(){
			    	if ($(this).attr("checked") == "checked") {
			    		p.ctypelogic=$(this).val();
			    	}
			    });
			    
			    p.csourcevalue = $('input[name=qsource]', g.mDiv).val();
			    p.cothervalue= $('input[name=qother]',g.mDiv).val();
			    p.cservicestate = $('select[name=qservstate]', g.mDiv).val();
				p.newp = 1;
				p.searchtype="advance";
				g.populate();
			});
			
			$('input[name=exphone]', g.mDiv).click(function (){
				p.cnamevalue = $('input[name=qname]',g.mDiv).val();
				p.csexvalue = $('select[name=qsex]', g.mDiv).val();
				p.cage1value = $('input[name=qage1]',g.mDiv).val();
				p.cage2value = $('input[name=qage2]', g.mDiv).val();
				p.cidcardvalue = $('input[name=qidcard]', g.mDiv).val();
				p.ctitlevalue = $('input[name=qtitle]', g.mDiv).val();
				p.cprovincevalue = province[$('select[name=qprovince]',g.mDiv).val()];
			    p.ccityvalue = $('select[name=qcity]',g.mDiv).val();
			    p.cpostvalue  = $('input[name=qpost]',g.mDiv).val();
			    p.cmobilevalue = $('input[name=qmobile]',g.mDiv).val();
			    p.cfixvalue = $('input[name=qfix]',g.mDiv).val();
			    p.cmailvalue = $('input[name=qmail]',g.mDiv).val();
			    p.cidvalue = $('input[name=qid]', g.mDiv).val();
			    p.caddrvalue= $('input[name=qaddr]',g.mDiv).val();
			    p.ccompanyvalue= $('input[name=qcompany]', g.mDiv).val();
			    p.ctypevalue=0;
			    $('input[name=qtypevalue]',g.mDiv).each(function(){
			    	if ($(this).attr("checked") == "checked") {
			    	    p.ctypevalue+=parseInt($(this).val());
			    	}
			    });
			    $('input[name=qlogicvalue]', g.mDiv).each(function(){
			    	if ($(this).attr("checked") == "checked") {
			    		p.ctypelogic=$(this).val();
			    	}
			    });
			    
			    p.csourcevalue = $('input[name=qsource]', g.mDiv).val();
			    p.cothervalue= $('input[name=qother]',g.mDiv).val();
			    p.cservicestate = $('select[name=qservstate]', g.mDiv).val();
				window.location.href="CustomerServlet?action=exmobile"+g.constructfilter();
			});
			
			$('input[name=exmail]', g.mDiv).click(function (){
				p.cnamevalue = $('input[name=qname]',g.mDiv).val();
				p.csexvalue = $('select[name=qsex]', g.mDiv).val();
				p.cage1value = $('input[name=qage1]',g.mDiv).val();
				p.cage2value = $('input[name=qage2]', g.mDiv).val();
				p.cidcardvalue = $('input[name=qidcard]', g.mDiv).val();
				p.ctitlevalue = $('input[name=qtitle]', g.mDiv).val();
				p.cprovincevalue = province[$('select[name=qprovince]',g.mDiv).val()];
			    p.ccityvalue = $('select[name=qcity]',g.mDiv).val();
			    p.cpostvalue  = $('input[name=qpost]',g.mDiv).val();
			    p.cmobilevalue = $('input[name=qmobile]',g.mDiv).val();
			    p.cfixvalue = $('input[name=qfix]',g.mDiv).val();
			    p.cmailvalue = $('input[name=qmail]',g.mDiv).val();
			    p.cidvalue = $('input[name=qid]', g.mDiv).val();
			    p.caddrvalue= $('input[name=qaddr]',g.mDiv).val();
			    p.ccompanyvalue= $('input[name=qcompany]', g.mDiv).val();
			    p.ctypevalue=0;
			    $('input[name=qtypevalue]',g.mDiv).each(function(){
			    	if ($(this).attr("checked") == "checked") {
			    	    p.ctypevalue+=parseInt($(this).val());
			    	}
			    });
			    $('input[name=qlogicvalue]', g.mDiv).each(function(){
			    	if ($(this).attr("checked") == "checked") {
			    		p.ctypelogic=$(this).val();
			    	}
			    });
			    
			    p.csourcevalue = $('input[name=qsource]', g.mDiv).val();
			    p.cothervalue= $('input[name=qother]',g.mDiv).val();
			    p.cservicestate = $('select[name=qservstate]', g.mDiv).val();
				window.location.href="CustomerServlet?action=exmail"+g.constructfilter();
			});
			
			$(g.gDiv).prepend(g.mDiv);
			if (p.showTableToggleBtn) {
				$(g.mDiv).append('<div class="ptogtitle" title="Minimize/Maximize Table"><span></span></div>');
				$('div.ptogtitle', g.mDiv).click(function () {
					$(g.gDiv).toggleClass('hideBody');
					$(this).toggleClass('vsble');
				});
			}
		}
		//setup cdrops
		g.cdropleft = document.createElement('span');
		g.cdropleft.className = 'cdropleft';
		g.cdropright = document.createElement('span');
		g.cdropright.className = 'cdropright';
		//add block
		g.block.className = 'gBlock';
		var gh = $(g.bDiv).height();
		var gtop = g.bDiv.offsetTop;
		$(g.block).css({
			width: g.bDiv.style.width,
			height: gh,
			background: 'white',
			position: 'relative',
			marginBottom: (gh * -1),
			zIndex: 1,
			top: gtop,
			left: '0px'
		});
		$(g.block).fadeTo(0, p.blockOpacity);
		// add column control
		if ($('th', g.hDiv).length) {
			g.nDiv.className = 'nDiv';
			g.nDiv.innerHTML = "<table cellpadding='0' cellspacing='0'><tbody></tbody></table>";
			$(g.nDiv).css({
				marginBottom: (gh * -1),
				display: 'none',
				top: gtop
			}).noSelect();
			var cn = 0;
			$('th div', g.hDiv).each(function () {
				var kcol = $("th[axis='col" + cn + "']", g.hDiv)[0];
				var chk = 'checked="checked"';
				if (kcol.style.display == 'none') {
					chk = '';
				}
				$('tbody', g.nDiv).append('<tr><td class="ndcol1"><input type="checkbox" ' + chk + ' class="togCol" value="' + cn + '" /></td><td class="ndcol2">' + this.innerHTML + '</td></tr>');
				cn++;
			});
			if ($.browser.msie && $.browser.version < 7.0) $('tr', g.nDiv).hover(function () {
				$(this).addClass('ndcolover');
			}, function () {
				$(this).removeClass('ndcolover');
			});
			$('td.ndcol2', g.nDiv).click(function () {
				if ($('input:checked', g.nDiv).length <= p.minColToggle && $(this).prev().find('input')[0].checked) return false;
				return g.toggleCol($(this).prev().find('input').val());
			});
			$('input.togCol', g.nDiv).click(function () {
				if ($('input:checked', g.nDiv).length < p.minColToggle && this.checked == false) return false;
				$(this).parent().next().trigger('click');
			});
			$(g.gDiv).prepend(g.nDiv);
			$(g.nBtn).addClass('nBtn')
				.html('<div></div>')
				.attr('title', 'Hide/Show Columns')
				.click(function () {
					$(g.nDiv).toggle();
					return true;
				}
			);
			if (p.showToggleBtn) {
				$(g.gDiv).prepend(g.nBtn);
			}
		}
		// add date edit layer
		$(g.iDiv).addClass('iDiv').css({
			display: 'none'
		});
		$(g.bDiv).append(g.iDiv);
		// add flexigrid events
		$(g.bDiv).hover(function () {
			$(g.nDiv).hide();
			$(g.nBtn).hide();
		}, function () {
			if (g.multisel) {
				g.multisel = false;
			}
		});
		$(g.gDiv).hover(function () {}, function () {
			$(g.nDiv).hide();
			$(g.nBtn).hide();
		});
		//add document events
		$(document).mousemove(function (e) {
			g.dragMove(e)
		}).mouseup(function (e) {
			g.dragEnd()
		}).hover(function () {}, function () {
			g.dragEnd()
		});
		//browser adjustments
		if ($.browser.msie && $.browser.version < 7.0) {
			$('.hDiv,.bDiv,.mDiv,.pDiv,.vGrip,.tDiv, .sDiv, .s1Div', g.gDiv).css({
				width: '100%'
			});
			$(g.gDiv).addClass('ie6');
			if (p.width != 'auto') {
				$(g.gDiv).addClass('ie6fullwidthbug');
			}
		}
		g.rePosDrag();
		g.fixHeight();
		//make grid functions accessible
		t.p = p;
		t.grid = g;
		// load data
		if (p.url && p.autoload) {
			g.populate();
		}
		return t;
	};
	var docloaded = false;
	$(document).ready(function () {
		docloaded = true
	});
	$.fn.flexigrid = function (p) {
		return this.each(function () {
			if (!docloaded) {
				$(this).hide();
				var t = this;
				$(document).ready(function () {
					$.addFlex(t, p);
				});
			} else {
				$.addFlex(this, p);
			}
		});
	}; //end flexigrid
	$.fn.flexReload = function (p) { // function to reload grid
		return this.each(function () {
			if (this.grid && this.p.url) this.grid.populate();
		});
	}; //end flexReload
	$.fn.flexOptions = function (p) { //function to update general options
		return this.each(function () {
			if (this.grid) $.extend(this.p, p);
		});
	}; //end flexOptions
	$.fn.flexToggleCol = function (cid, visible) { // function to reload grid
		return this.each(function () {
			if (this.grid) this.grid.toggleCol(cid, visible);
		});
	}; //end flexToggleCol
	$.fn.flexAddData = function (data) { // function to add data to grid
		return this.each(function () {
			if (this.grid) this.grid.addData(data);
		});
	};
	$.fn.noSelect = function (p) { //no select plugin by me :-)
		var prevent = (p == null) ? true : p;
		if (prevent) {
			return this.each(function () {
				if ($.browser.msie || $.browser.safari) $(this).bind('selectstart', function () {
					return false;
				});
				else if ($.browser.mozilla) {
					$(this).css('MozUserSelect', 'none');
					$('body').trigger('focus');
				} else if ($.browser.opera) $(this).bind('mousedown', function () {
					return false;
				});
				else $(this).attr('unselectable', 'on');
			});
		} else {
			return this.each(function () {
				if ($.browser.msie || $.browser.safari) $(this).unbind('selectstart');
				else if ($.browser.mozilla) $(this).css('MozUserSelect', 'inherit');
				else if ($.browser.opera) $(this).unbind('mousedown');
				else $(this).removeAttr('unselectable', 'on');
			});
		}
	}; //end noSelect
})(jQuery);