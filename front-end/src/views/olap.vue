<template>
    <v-card theme="light">
        <v-card-title>
            <v-row>
                <v-col>
                    <h1 class="title">{{olapName}}</h1>
                </v-col>
                <v-col right>
                    <v-row>

                    </v-row>
                    <p>
                        settings
                    </p>
                    <v-switch type="success" :checked="settings" v-model="settings"></v-switch>
                </v-col>
            </v-row>
        </v-card-title>
        <v-card-text v-if="!settings">
            <v-textarea class="textarea" placeholder="Textarea" v-model="mdxQuery" style="margin-bottom : 20px;"
                        disabled></v-textarea>
            <div id="map" style="height: 700px; width: 100%;"></div>
        </v-card-text>
        <!-- settings -->
        <v-card-text v-else>
            <v-text-field class="input" type="text" :label="'Title Name'" placeholder="Text input" v-model="olapName">
            </v-text-field>
            <!-- Mdx Query -->
            <v-textarea class="textarea" placeholder="Textarea" :label="'MDX Query Editor'"
                        v-model="mdxQuery"></v-textarea>
            <div>
                <v-popover
                        offset="16"
                        style="float: right"
                        placement="left-center"
                >
                    <div style="height: 20px; width: 20px;" v-bind:style="{backgroundColor: line.maximumColors.hex}"/>

                    <template slot="popover">
                        <sketch-picker v-model="line.maximumColors"/>
                    </template>
                </v-popover>
                <p class="subtitle is-5">최대 :</p>
                <!-- gradient end color -->
                <v-popover
                        offset="16"
                        style="float: right"
                        placement="left-center"
                >
                    <div style="height: 20px; width: 20px;" v-bind:style="{backgroundColor: line.minimumColors.hex}"/>

                    <template slot="popover">
                        <sketch-picker v-model="line.minimumColors"/>
                    </template>
                </v-popover>
                <p class="subtitle is-5">최소 : </p>
            </div>
        </v-card-text>
    </v-card>
    <!-- setting end -->
</template>

<script>
    /* eslint-disable */
    var Popper = require('popper.js');
    import Plotly from 'plotly.js'
    import wkx from 'wkx'
    import {Sketch} from 'vue-color'
    import draggable from 'vuedraggable'
    import xmla4js from 'xmla4js'
    import Cleave from 'vue-cleave'
    import 'cleave.js/dist/addons/cleave-phone.cn'
    import {VTooltip, VPopover, VClosePopover} from 'v-tooltip'
    //  import ContextMenu from 'ol-contextmenu'

    export default {
        components: {
            'sketch-picker': Sketch,
            draggable,
            xmla4js,
            Cleave,
            Sketch,
            VPopover
        },
        computed: {},
        watch:{
            settings(newVal) {
                var me = this
                if(newVal == false) {
                    console.log(newVal)
                    me.$nextTick(function () {
                        me.drawMap();
                    })
                }
            }
        },
        props: {
            componentWidth: Number,
            containerWidth: Number,
            Id: Number,
            mode: String,
            columnNav: Array,
            rowsNav: Array,
            filterNav: Array,
            tmp: Object
        },
        mounted() {
            var me = this
            this.$nextTick(function () {
                me.drawMap();
                me.mdxQuery = 'SELECT {[Measures].[12]} ON COLUMNS, {[Estate].[서울특별시]} ON ROWS FROM [Environment]'
            })
        },
        data() {
            return {
                timeQuery: '',
                olapName: 'New Olap',
                beforeComponentWidth: 0,
                usedMdxQuery: '',
                settings: false,
                navChange: false,
                editable: true,
                vmap: [],
                myArray: [],
                map: [],
                firstMode: '',
                startPopper: false,
                endPopper: false,
                contextmenu: [],
                rowsBack: [],
                columnBack: [],
                filterBack: [],
                schemas: {
                    table: {
                        name: [],
                        level: []
                    },

                    measure: {
                        name: [],
                    },
                    dimension: {
                        name: [],
                        level: []
                    }
                },
                editRowsMdx: '',
                cube: '',
                modes: [],
                mdxQuery: '',
                chartList: ['Data Table', 'Pie', 'Doughnut', 'Bar'],
                sizes: ['Small', 'Medium', 'Large'],
                size: '',
                chart: '',
                address: '',
                sd: '',
                sig: '',
                dong: '',
                detailAddress: '',
                x: '',
                y: '',
                selectMeasure: [],
                selectRows: [],
                editColumnsMdx: '',
                geom: [],
                line: {
                    maximumColors: {
                        hex: '#194d33',
                        hsl: {h: 150, s: 0.5, l: 0.2, a: 1},
                        hsv: {h: 150, s: 0.66, v: 0.30, a: 1},
                        rgba: {r: 25, g: 77, b: 51, a: 1},
                        a: 1
                    },
                    minimumColors: {
                        hex: '#194d33',
                        hsl: {h: 150, s: 0.5, l: 0.2, a: 1},
                        hsv: {h: 150, s: 0.66, v: 0.30, a: 1},
                        rgba: {r: 25, g: 77, b: 51, a: 1},
                        a: 1
                    }
                }
            }
        },
        methods: {
            drawMap: function () {
                var me = this;
                var Base = new ol.layer.Tile({
                    name: "Base",
                    source: new ol.source.XYZ({
                        url: 'http://api.vworld.kr/req/wmts/1.0.0/D01A2D82-B1E6-3340-84D4-462002F06506/Base/{z}/{y}/{x}.png'
                    })
                });
                var olView = new ol.View({
                    center: ol.proj.transform([127.100616, 37.402142], 'EPSG:4326', 'EPSG:3857'),//좌표계 변환
                    zoom: 6
                })
                me.map = new ol.Map({
                    layers: [Base],
                    target: 'map',
                    view: olView,
                    controls: ol.control.defaults({
                        attributionOptions: {
                            collapsible: false
                        }
                    }),
                });
                var contextmenu = new ContextMenu({
                    width: 170,
                    defaultItems: true, // defaultItems are (for now) Zoom In/Zoom Out
                    items: [
                        {
                            text: 'Roll Up',
                            classname: 'some-style-class', // add some CSS rules
                            callback: me.rollUp // `center` is your callback function
                        },
                        {
                            text: 'Drill Down',
                            classname: 'some-style-class', // you can add this icon with a CSS class
                                                           // instead of `icon` property (see next line)
                            callback: me.drillDown
                        },
                        '-' // this is a separator
                    ]
                });

                me.map.addControl(contextmenu);
                me.map.getViewport().addEventListener('contextmenu', function (evt) {
                    evt.preventDefault();

                    me.x = ol.proj.toLonLat(me.map.getEventCoordinate(evt))[0]
                    me.y = ol.proj.toLonLat(me.map.getEventCoordinate(evt))[1]
                })

                contextmenu.on('beforeopen', function (evt) {
                    var feature = me.map.forEachFeatureAtPixel(evt.pixel, function (ft, l) {
                        return ft;
                    });

                    if (feature) { // open only on features
                        contextmenu.enable();
                    } else {
                        contextmenu.disable();
                    }
                });
            },
            getSchemas: function () {
                var me = this
            },
            rollUp: function () {
                var me = this
                var locationObj = []
                if (me.tmp.values[0].EMD != null) {
                    me.editRowsMdx = '{[' + me.rowsNav[0] + '].[All ' + me.rowsNav[0] + 's].[' + me.sd + '].Children}'
                    me.submit()
                } else if (me.tmp.values[0].SGG != null) {
                    me.editRowsMdx = '{[' + me.rowsNav[0] + '].[' + me.sd + ']}'
                    me.submit()
                } else if (me.tmp.values[0].SD != null) {
                }
            },
            drillDown: function () {
                var me = this;
                // console.log(me.mdxQuery)

                me.getAddress().then(function () {
                    var locationObj = []
                    if (me.tmp.values[0].EMD != null) {
                        me.editRowsMdx = '{[' + me.rowsNav[0] + '].[All ' + me.rowsNav[0] + 's].[' + me.sd + '].[' + me.sig + '].[' + me.emd + '].Children}'
                        me.mdxQuery = me.computedMdxQuery
                        me.submit()
                    } else if (me.tmp.values[0].SGG != null) {
                        me.editRowsMdx = '{[' + me.rowsNav[0] + '].[All ' + me.rowsNav[0] + 's].[' + me.sd + '].[' + me.sig + '].Children}'
                        me.mdxQuery = me.computedMdxQuery
                        me.submit()
                    } else if (me.tmp.values[0].SD != null) {
                        me.editRowsMdx = '{[' + me.rowsNav[0] + '].[' + me.sd + '].[' + me.sig + ']}'
                        me.mdxQuery = me.computedMdxQuery.replace(/(\r\n\t|\n|\r\t)/gm, "");
                        me.submit()
                    }
                })
            },
            callMdx: function () {
                var me = this
                return new Promise(function (resolve, reject) {
                    $.ajax({
                        type: "post",
                        url: 'http://localhost:8090/query',
                        contentType: "application/json; charset=UTF-8",
                        data: JSON.stringify({
                            'connectionName': 'marmot',
                            'query': me.mdxQuery,
                            'tidy': {
                                'enabled': true,
                                'simplifyNames': true
                            }
                        }),
                        success: function (data) {
                            console.log(data)
                            me.tmp = data;
                            resolve(data);
                            // data.values.forEach(function(tmp) {
                            //   if(tmp.SD == '대전광역시') {
                            //     console.log("-----------------")
                            //     console.log(me.convertGeom(tmp.geom))
                            //     // me.drawPolygon(convertData, resultDataList[emd_kor_na])
                            //     console.log(tmp.geom)
                            //     console.log("-----------------")
                            //   }
                            //
                            // })
                        }
                    });
                })
            },
            convertGeom: function (wkb) {
                var me = this;
                var Buffer = require('buffer').Buffer;
                var wkx = require('wkx');

                var wkbBuffer = new Buffer(wkb, 'hex');
                var geometry = wkx.Geometry.parse(wkbBuffer);

                return geometry.toWkt()

            },
            sortData: function (minData, maxData, thisData, geo) {
                var me = this
                //300 200 - 100 --> 가격 - 최저가격 / 최고가격 - 최저가격 = >

                var resultData = (thisData - minData) / (maxData - minData)
                return resultData
            },
            getAddress: function () {
                var me = this
                return new Promise(function (resolve, reject) {
                    $.ajax({
                        type: "get",
                        url: 'http://api.vworld.kr/req/address?service=address&request=GetAddress&key=D01A2D82-B1E6-3340-84D4-462002F06506&format=json&point=' + me.x + ',' + me.y + '&type=both',
                        dataType: "jsonp",
                        jsonp: "callback",
                        contentType: "text/json;charset=UTF-8",
                        success: function (data) {
                            var result = data.response.result[0];
                            me.address = result.text;
                            me.sd = result.structure.level1;
                            me.sig = result.structure.level2;
                            me.emd = result.structure.level4L;
                            me.detailAddress = result.structure.level5;

                            resolve()
                        }
                    })
                });
            },
            multiDraw: function (coordinates) {
                // Coordinates 의 형태 [ [x,y] ]
                var me = this;
                for (var i = 0; i < me.coordinates.length; i++) {
                    me.drawPolygon(me.coordinates[i].geometry)
                }

            },
            colorMath: function (returnData, color) {
                var me = this;

                //0~1  ==> 색범위, (레드맥심 - 레드미니) * 0~1 + 레드 미니 => 레드
                if (color == 'red') {
                    var red = (me.line.maximumColors.rgba.r - me.line.minimumColors.rgba.r) * returnData + me.line.minimumColors.rgba.r
                    return red
                } else if (color == 'green') {
                    var green = (me.line.maximumColors.rgba.g - me.line.minimumColors.rgba.g) * returnData + me.line.minimumColors.rgba.g
                    return green
                } else if (color == 'blue') {
                    var blue = (me.line.maximumColors.rgba.b - me.line.minimumColors.rgba.b) * returnData + me.line.minimumColors.rgba.b
                    return blue
                }
            },
            clearPolygon: function () {
                var me = this
                let vectorLayerArray = new Array();
                me.map.getLayers().getArray().some(function (layer, i, array) {
                    if (layer instanceof ol.layer.Vector) {
                        vectorLayerArray.push(layer);
                    }
                }, this);

                for (var i = 0; i < vectorLayerArray.length; i++) {
                    me.map.removeLayer(vectorLayerArray[i]);
                }
            },
            drawPolygon: function (coordinates, resultData) {

                var me = this;
                var wkt = coordinates
                var format = new ol.format.WKT();

                if (resultData == 0) {
                    var red = me.line.minimumColors.rgba.r
                    var green = me.line.minimumColors.rgba.g
                    var blue = me.line.minimumColors.rgba.b
                } else if (resultData == 1) {
                    var red = me.line.maximumColors.rgba.r
                    var green = me.line.maximumColors.rgba.g
                    var blue = me.line.maximumColors.rgba.b
                } else {
                    var red = me.colorMath(resultData, 'red')
                    var green = me.colorMath(resultData, 'green')
                    var blue = me.colorMath(resultData, 'blue')
                }

                var feature = format.readFeature(wkt, {
                    dataProjection: 'EPSG:4326',
                    featureProjection: 'EPSG:3857'
                });

                var styles = new ol.style.Style({
                    stroke: new ol.style.Stroke({
                        color: 'rgba(' + red + ',' + green + ',' + blue + ', 1)',
                        width: '2'
                    }),
                    fill: new ol.style.Fill({
                        color: 'rgba(' + red + ',' + green + ',' + blue + ', 0.2)',
                    })
                })

                var vector = new ol.layer.Vector({
                    source: new ol.source.Vector({
                        features: [feature],
                    }),
                    style: styles

                });

                me.map.addLayer(vector);

            }
        }
    }
</script>

<style scoped>
    .context_menu {
        background-color: white;
        border: 1px solid gray;
    }

    .demo-box {
        width: 100%;
        height: 100%;
    }

    .card {
        position: relative;
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-orient: vertical;
        -webkit-box-direction: normal;
        -ms-flex-direction: column;
        flex-direction: column;
        min-width: 0;
        word-wrap: break-word;
        background-color: #fff;
        background-clip: border-box;
        border: 1px solid rgba(0, 0, 0, 0.125);
        border-radius: 0.25rem;
    }

    .card-header {
        padding: 0.75rem 1.25rem;
        margin-bottom: 0;
        background-color: rgba(255, 255, 255, 0.03);
        border-bottom: 1px solid rgba(0, 0, 0, 0.125);
    }

    .card-body {
        -webkit-box-flex: 1;
        -ms-flex: 1 1 auto;
        flex: 1 1 auto;
        padding: 1.25rem;
    }

    div.ol-overlaycontainer-stopevent {
        display: none !important;
    }

    .context_menu_item {
        padding: 3px 6px;
    }

    .context_menu_item:hover {
        background-color: #CCCCCC;
    }

    .context_menu_separator {
        background-color: gray;
        height: 1px;
        margin: 0;
        padding: 0;
    }

    #clearDirectionsItem, #getDirectionsItem {
        display: none;
    }

    /*	end styles for the ContextMenu	*/

    #map_container {
        height: 100%;
    }

    .popover-inner {
        background: #f9f9f9;
        color: black;
        padding: 24px;
        border-radius: 5px;
        box-shadow: 0 5px 30px rgba(0, 0, 0, .1);
    }

    .popover .popover-arrow {
        border-color: #f9f9f9;
    }

    .flip-list-move {
        transition: transform 0.5s;
    }

    .no-move {
        transition: transform 0s;
    }

    .ghost {
        opacity: .5;
        background: #C8EBFB;
    }

    .list-group {
        min-height: 20px;
    }

    .list-group-item {
        cursor: move;
    }

    .list-group-item i {
        cursor: pointer;
    }


</style>
