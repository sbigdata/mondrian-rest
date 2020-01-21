<template>
    <div>
        <v-overlay :value="overlay">
            <v-row>
                <v-col>
                    <v-progress-circular
                            size="70"
                            width="7"
                            indeterminate
                            color="primary"
                    ></v-progress-circular>
                </v-col>


            </v-row>
            <v-row>
                <v-col>
                    {{runningTime}} 초 진행중...

                </v-col>
            </v-row>

            <!--<v-btn small-->
            <!--icon-->
            <!--@click="overlay = false"-->
            <!--&gt;-->
            <!--<v-icon>mdi-close</v-icon>-->
            <!--</v-btn>-->


        </v-overlay>
        <v-snackbar
                v-model="snackbar"
        >
            {{ runningTime }} 초 소요되었습니다.
            <v-btn
                    color="pink"
                    text
                    @click="snackbar = false"
            >
                Close
            </v-btn>
        </v-snackbar>
        <v-row>
            <v-col cols="12"
                   md="8">
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
                        <v-textarea class="textarea" placeholder="Textarea" v-model="mdxQuery"
                                    style="margin-bottom : 20px;"
                                    disabled></v-textarea>
                        <v-col cols="12">
                            <v-subheader class="pl-0">시간 선택</v-subheader>
                            <br/>
                            <v-slider
                                    v-model="slider"
                                    thumb-label="always"
                                    max="23"
                            ></v-slider>
                        </v-col>
                        <v-row>
                            <v-col class="d-flex" cols="12" sm="3">
                                <v-select
                                        :items="quarterList"
                                        label="분기 선택"
                                        v-model="quarter"
                                        outlined
                                ></v-select>
                            </v-col>
                            <v-col class="d-flex" cols="6" sm="3">
                                <v-select
                                        v-if="quarter"
                                        :items="monthList"
                                        label="월별 선택"
                                        v-model="month"
                                        outlined
                                ></v-select>
                            </v-col>
                        </v-row>
                        <div id="map" style="height: 500px"></div>
                        <!--<vl-map :load-tiles-while-animating="true" :load-tiles-while-interacting="true"-->
                        <!--data-projection="EPSG:4326" style="height: 400px">-->
                        <!--<vl-view :zoom.sync="zoom" :center.sync="center" :rotation.sync="rotation"></vl-view>-->
                        <!--&lt;!&ndash;<vl-layer-tile>&ndash;&gt;-->
                        <!--&lt;!&ndash;<vl-source-sputnik></vl-source-sputnik>&ndash;&gt;-->
                        <!--&lt;!&ndash;</vl-layer-tile>&ndash;&gt;-->
                        <!--<vl-layer-tile id="wmts">-->
                        <!--<vl-source-wmts :url="url" :layer-name="'vworld'"-->
                        <!--:matrix-set="'EPSG:4326'" :format="'img/png'"-->
                        <!--:style-name="'default'"></vl-source-wmts>-->
                        <!--</vl-layer-tile>-->
                        <!--</vl-map>-->

                    </v-card-text>
                    <!-- settings -->
                    <v-card-text v-else>
                        <v-text-field class="input" type="text" :label="'Title Name'" placeholder="Text input"
                                      v-model="olapName">
                        </v-text-field>
                            <!--<p>{{ testMdx || 'null' }}</p>-->
                            <v-radio-group v-model="testMdx" :mandatory="false">
                                <v-radio label="marmot" value="marmot"></v-radio>
                                <v-radio label="postgresql" value="postgresql"></v-radio>
                            </v-radio-group>
                        <!-- Mdx Query -->
                        <v-textarea class="textarea" placeholder="Textarea" :label="'MDX Query Editor'"
                                    v-model="mdxQuery"></v-textarea>
                        <div>
                            <v-popover
                                    offset="16"
                                    style="float: right"
                                    placement="left-center"
                            >
                                <div style="height: 20px; width: 20px;"
                                     v-bind:style="{backgroundColor: line.maximumColors.hex}"/>

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
                                <div style="height: 20px; width: 20px;"
                                     v-bind:style="{backgroundColor: line.minimumColors.hex}"/>
                                <template slot="popover">
                                    <sketch-picker v-model="line.minimumColors"/>
                                </template>
                            </v-popover>
                            <p class="subtitle is-5">최소 : </p>
                        </div>
                    </v-card-text>
                </v-card>
            </v-col>
            <v-col
                    cols="6"
                    md="4">
                <v-card
                        theme="light">
                    <v-simple-table dense>
                        <template v-slot:default>
                            <thead>
                            <tr>
                                <th class="text-left">Name</th>
                                <th class="text-left">{{measureName}} Result</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="item in tmp.values" :key="item.name">
                                <td v-if="item.EMD">{{ item.SD }} {{ item.SIG }} {{item.EMD}}</td>
                                <td v-else-if="item.SIG">{{ item.SD }} {{ item.SIG }}</td>
                                <td v-else-if="item.SD">{{ item.SD }}</td>
                                <td>{{ findResult(item) }}</td>
                            </tr>
                            </tbody>
                        </template>
                    </v-simple-table>
                </v-card>
            </v-col>
        </v-row>
    </div>

    <!-- setting end -->
</template>

<script>
    /* eslint-disable */
    // import proj4 from 'proj4'
    // localhost -> 192.168.157.43
    //
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
            VPopover,
        },
        computed: {
            monthList() {
                var me = this
                switch (me.quarter) {
                    case '없음' :
                        me.quarter = ''
                        break;
                    case 1 :
                        return [1, 2, 3]
                        break;
                    case 2 :
                        return [4, 5, 6]
                        break;
                    case 3 :
                        return [7, 8, 9]
                        break;
                    case 4 :
                        return [10, 11, 12]
                        break;
                }
            }
        },
        watch: {
            settings(newVal) {
                var me = this
                if (newVal == false) {
                    // console.log(newVal)
                    me.runningTime = 0
                    me.$nextTick(function () {
                        // me.testCall()
                        me.submit()
                        me.drawMap();
                    })
                }
            },
            quarter(newVal) {
                var me = this
                me.mdxQuery = 'SELECT ' + me.onColumn + ' ON COLUMNS, ' + me.onRows + ' ON ROWS FROM ' + me.cubeName + ' WHERE ([Time].[2015].[' + newVal + '])';
            },
            month(newVal) {
                var me = this
                me.mdxQuery = 'SELECT ' + me.onColumn + ' ON COLUMNS, ' + me.onRows + ' ON ROWS FROM ' + me.cubeName + ' WHERE ([Time].[2015].[' + me.quarter + '].[' + newVal + '])';

            },
            mdxQuery() {
                var me = this
                var queryList = [];
                var tmpQuery = JSON.parse(JSON.stringify(me.mdxQuery))
                var tmpQuery2 = tmpQuery.replace(/SELECT /gi, '');
                me.onColumn = tmpQuery2.split(/ ON COLUMNS, /gi)[0];
                var tmpQuery3 = tmpQuery2.split(/ ON COLUMNS, /gi)[1].split(/ ON ROWS FROM /gi);
                me.onRows = tmpQuery3[0]
                var tmpQuery4 = tmpQuery3[1].split(/ WHERE /gi);
                me.cubeName = tmpQuery4[0]
                me.onWhere = tmpQuery4[1]
                me.mdxQuery = 'SELECT ' + me.onColumn + ' ON COLUMNS, ' + me.onRows + ' ON ROWS FROM ' + me.cubeName + ' WHERE ' + me.onWhere
            },
            slider(newVal) {
                var me = this
                if (newVal.toString().length == 1) {
                    var time = '0'.concat(newVal)
                } else {
                    time = newVal
                }
                me.mdxQuery = 'SELECT {[Measures].[' + time + ']} ON COLUMNS, ' + me.onRows + ' ON ROWS FROM ' + me.cubeName + ' WHERE ' + me.onWhere
            },
        },
        props: {
            componentWidth: Number,
            containerWidth: Number,
            Id: Number,
            mode: String,
            columnNav: Array,
            rowsNav: Array,
            filterNav: Array,
        },
        created() {
            var me = this
            this.$nextTick(function () {
                me.drawMap();
                // me.drawPolygon()
                // me.testCall()
                // me.testDraw()
            })
        },
        mounted() {
            var me = this

            this.$nextTick(async function () {
                var me = this
                me.mdxQuery = 'SELECT {[Measures].[12]} ON COLUMNS, {[Estate].[All Estates].children} ON ROWS FROM [Environment]'
                await me.callSdCode()
                    .then(async function () {
                        for (var key in me.geoCodeList) {
                            console.log(me.geoCodeList[key]);
                            await me.callSigCode(me.geoCodeList[key])
                        }
                    })
                // console.log("aa")
            })
        },
        data() {
            return {
                testMdx: '',
                overlay: false,
                geoCodeList: {},
                tmp: {},
                measureName: '',
                slider: '12',
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
                snackbar: false,
                quarter: '',
                month: '',
                onColumn: '',
                onRows: '',
                cubeName: '',
                onWhere: '',
                editRowsMdx: '',
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
                zoom: 2,
                center: [0, 0],
                rotation: 0,
                geolocPosition: undefined,
                runningTime: 0,
                quarterList: ['없음', 1, 2, 3, 4],
                selectMeasure: [],
                selectRows: [],
                editColumnsMdx: '',
                geom: [],
                sdcode: '',
                sigcode: '',
                emdcode: '',
                url: 'http://api.vworld.kr/req/wmts/1.0.0/D01A2D82-B1E6-3340-84D4-462002F06506/Base/{z}/{y}/{x}.png',
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
            findResult(item) {
                return eval('item[' + this.measureName + ']');
            },
            testCall: function () {
                var me = this
                var queryList = [];
                var tmpQuery = JSON.parse(JSON.stringify(me.mdxQuery))
                var tmpQuery2 = tmpQuery.replace(/SELECT /gi, '');
                me.onColumn = tmpQuery2.split(/ ON COLUMNS, /gi)[0];
                var tmpQuery3 = tmpQuery2.split(/ ON COLUMNS, /gi)[1].split(/ ON ROWS FROM /gi);
                me.onRows = tmpQuery3[0]
                var tmpQuery4 = tmpQuery3[1].split(/ WHERE /gi);
                me.cubeName = tmpQuery4[0]
                me.onWhere = tmpQuery4[1]
                me.mdxQuery = 'SELECT ' + me.onColumn + ' ON COLUMNS, ' + me.onRows + ' ON ROWS FROM ' + me.cubeName + ' WHERE ' + me.onWhere
                // console.log(('SELECT ' + me.onColumn + ' ON COLUMNS, ' + me.onRows + ' ON ROWS FROM ' + me.cubeName + ' WHERE ' + me.onWhere) == me.mdxQuery);
                // me.submit();
                me.drawMap()
            },
            drawMap: function () {
                // http://api.vworld.kr/req/image?service=image&request=getmap&key=인증키&[요청파라미터]
                var me = this;

                // proj4.defs( "EPSG:5186", "+proj=tmerc +lat_0=38 +lon_0=127 +k=1 +x_0=200000 +y_0=600000 +ellps=GRS80 +units=m +no_defs" ); // 5186 좌표선언
                //
                // var baseLayer= new ol.layer.Tile({ // TMS 레이어
                //     source: new ol.source.OSM({
                //         url: "http://xdworld.vworld.kr:8080/2d/Base/service/{z}/{x}/{y}.png"
                //     })
                // });
                //
                // var map = new ol.Map({
                //     layers: [baseLayer], // 지도 레이어
                //     target: "map", // 맵 표현 DIV 명
                //     view: new ol.View({ // 맵 뷰
                //         projection : new ol.proj.Projection({
                //             code : "EPSG:5186", // 지도 좌표계
                //             units : "m",
                //             axisOrientation : "enu"
                //         }),
                //         center: ol.proj.fromLonLat([127.000042, 37.558324], "EPSG:5186"), // 맵 센터 위경도
                //         minZoom : 7, // 맵 최소 줌 레벨
                //         maxZoom : 20, // 맵 최대 줌 레벨
                //         zoom: 10 // 맵 초기 줌 레벨
                //     })
                // });

                // var baseLayer= new ol.layer.Tile({ // TMS 레이어
                //     source: new ol.source.OSM({
                //         url: "http://xdworld.vworld.kr:8080/2d/Base/service/{z}/{x}/{y}.png"
                //     })
                // });

                proj4.defs("EPSG:5186", "+proj=tmerc +lat_0=38 +lon_0=127 +k=1 +x_0=200000 +y_0=600000 +ellps=GRS80 +units=m +no_defs"); // 5186 좌표선언
                var Base = new ol.layer.Tile({
                    name: "Base",
                    source: new ol.source.XYZ({
                        url: 'http://api.vworld.kr/req/wmts/1.0.0/D01A2D82-B1E6-3340-84D4-462002F06506/Base/{z}/{y}/{x}.png'
                    })
                });

                var olView = new ol.View({ // 맵 뷰
                    projection: new ol.proj.Projection({
                        code: "EPSG:5186", // 지도 좌표계
                        units: "m",
                        axisOrientation: "enu"
                    }),
                    center: ol.proj.fromLonLat([127.000042, 37.558324], "EPSG:5186"), // 맵 센터 위경도
                    zoom: 10 // 맵 초기 줌 레벨
                })
                // console.log(ol.proj.fromLonLat([127.000042, 37.558324], "EPSG:5186"))
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
                    me.x = ol.proj.fromLonLat(ol.proj.toLonLat(me.map.getEventCoordinate(evt), "EPSG:5186"), "EPSG:5186")[0]
                    me.y = ol.proj.fromLonLat(ol.proj.toLonLat(me.map.getEventCoordinate(evt), "EPSG:5186"), "EPSG:5186")[1]

                    console.log(me.x, me.y)
                })

                contextmenu.on('beforeopen', function (evt) {
                    // var feature = me.map.forEachFeatureAtPixel(evt.pixel, function (ft, l) {
                    //     return ft;
                    // });

                    // if (feature) { // open only on features
                    contextmenu.enable();
                    // } else {
                    //     contextmenu.disable();
                    // }
                });


            },
            getSchemas: function () {
                var me = this
            },
            rollUp: function () {
                var me = this
                var locationObj = []
                me.runningTime = 0

                console.log(me.onRows)
                var temp = me.onRows.split('.')
                var tempRows = '';

                temp.splice(temp.length - 2, 1)
                console.log(temp)
                var final = temp.length
                var i = 0
                temp.forEach(function (item) {
                    console.log(item)
                    if (final - 1 != i) {
                        tempRows = tempRows + (item + '.')
                        i++
                    } else {
                        tempRows = tempRows + item
                    }
                })
                console.log(tempRows)
                if (temp.length > 2) {
                    if (me.tmp.values[0].EMD != null || me.tmp.values[0].SIG != null || me.tmp.values[0].SD != null) {
                        me.mdxQuery = 'SELECT ' + me.onColumn + ' ON COLUMNS, ' + tempRows + ' ON ROWS FROM ' + me.cubeName + ' WHERE ' + me.onWhere

                        me.submit()
                    }
                }

            },
            drillDown: function () {
                var me = this;
                // console.log(me.mdxQuery)
                me.runningTime = 0

                me.getAddress().then(function () {
                    var locationObj = []
                    if (me.tmp.values[0].EMD != null) {
                        me.mdxQuery = 'SELECT ' + me.onColumn + ' ON COLUMNS, ' + me.onRows.replace('.children}', '.[') + me.emd + '].children} ON ROWS FROM ' + me.cubeName + ' WHERE ' + me.onWhere
                        me.submit()
                    } else if (me.tmp.values[0].SIG != null) {
                        me.mdxQuery = 'SELECT ' + me.onColumn + ' ON COLUMNS, ' + me.onRows.replace('.children}', '.[') + me.sig + '].children} ON ROWS FROM ' + me.cubeName + ' WHERE ' + me.onWhere
                        me.submit()
                    } else if (me.tmp.values[0].SD != null) {
                        me.mdxQuery = 'SELECT ' + me.onColumn + ' ON COLUMNS, ' + me.onRows.replace('.children}', '.[') + me.sd + '].children} ON ROWS FROM ' + me.cubeName + ' WHERE ' + me.onWhere
                        me.submit()
                    }
                })
            },
            callMdx: function () {
                var me = this
                me.overlay = true;
                return new Promise(function (resolve, reject) {
                    var timeCheck = setInterval(function () {
                        var beforeTime = JSON.parse(JSON.stringify(me.runningTime))
                        me.runningTime = +(beforeTime + 0.1).toFixed(1);
                    }, 100); // 0.1초
                    if(me.testMdx == 'marmot') {
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
                                //[{"12":3.9285903638563684}] All Estate
                                // console.log(data)
                                console.log(data)
                                for (var i = 0; i < data.values.length; i++) {
                                    eval("data.values[i][" + me.measureName + "] = " + "data.values[i][" + me.measureName + "].toFixed(2)")
                                }
                                me.tmp = data;
                                clearInterval(timeCheck);
                                me.overlay = false

                                me.snackbar = true
                                resolve();
                            }
                        });
                    } else if (me.testMdx == 'postgresql') {
                        $.ajax({
                            type: "post",
                            url: 'http://localhost:8091/query',
                            contentType: "application/json; charset=UTF-8",
                            data: JSON.stringify({
                                'connectionName': 'postgresql',
                                'query': me.mdxQuery,
                                'tidy': {
                                    'enabled': true,
                                    'simplifyNames': true
                                }
                            }),
                            success: function (data) {
                                //[{"12":3.9285903638563684}] All Estate
                                // console.log(data)
                                console.log(data)
                                for (var i = 0; i < data.values.length; i++) {
                                    eval("data.values[i][" + me.measureName + "] = " + "data.values[i][" + me.measureName + "].toFixed(2)")
                                }
                                me.tmp = data;
                                clearInterval(timeCheck);
                                me.overlay = false

                                me.snackbar = true
                                resolve();
                            }
                        });
                    }

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
            getKeyByValue: function (object, value) {
                return Object.keys(object).find(key => object[key] === value);
            },
            callSdCode() {
                var me = this
                return new Promise(function (resolve, reject) {
                    $.ajax({
                        type: "get",
                        url: 'http://localhost:8090/sd/',
                        contentType: "application/json; charset=UTF-8",
                    }).done(function (result) {
                        // console.log(result)
                        result._embedded.sd.forEach(function (item) {
                            var temp = item._links.self.href.split('/')
                            me.geoCodeList[item.name] = {code: temp[temp.length - 1], children: {}}

                            resolve()
                        })
                    }).catch(e => console.log(e));
                });
            },
            callSigCode(sdCode, key) {
                var me = this;
                return new Promise(function (resolve) {
                    $.ajax({
                        type: "get",
                        url: 'http://localhost:8090/sig/search/findBySdCode?sdCode=' + sdCode.code,
                        contentType: "application/json; charset=UTF-8",
                    }).done(function (result) {
                        // var temp = result._links.self.href.split('/')
                        result._embedded.sig.forEach(function (item) {
                            var temp = item._links.self.href.split('/')
                            sdCode.children[item.name] = {code: temp[temp.length - 1]}

                            resolve()
                        })
                        resolve()
                    });
                })
            },
            submit: function () {
                var me = this;
                var resultDataList = {}
                me.usedMdxQuery = me.mdxQuery

                me.clearPolygon();

                var measure = me.onColumn.replace("[Measures].", "").replace("[", "").replace("]", "").replace("{", "").replace("}", "");
                me.measureName = measure

                me.callMdx().then(function () {
                    // Data 정렬
                    var tmpPrice = [];
                    for (var i = 0; i < me.tmp.values.length; i++) {
                        eval("tmpPrice.push(me.tmp.values[i][" + measure + "]);");
                    }

                    var minData = Math.min.apply(null, tmpPrice);
                    var maxData = Math.max.apply(null, tmpPrice);

                    for (var e = 0; e < me.tmp.values.length; e++) {
                        if (me.tmp.values[e].EMD != undefined) {
                            var sortKey = me.tmp.values[e].EMD;
                            if (me.tmp.values.length > 1) {
                                var sortValue = eval("me.sortData(minData, maxData, me.tmp.values[e][" + measure + "], me.tmp.values[e].EMD)");
                                resultDataList[sortKey] = sortValue
                            } else {
                                resultDataList[sortKey] = 1
                            }
                        } else if (me.tmp.values[e].SIG != undefined) {
                            var sortKey = me.tmp.values[e].SIG;
                            if (me.tmp.values.length > 1) {
                                var sortValue = eval("me.sortData(minData, maxData, me.tmp.values[e][" + measure + "], me.tmp.values[e].SIG)");
                                resultDataList[sortKey] = sortValue
                            } else {
                                resultDataList[sortKey] = 1
                            }
                        } else if (me.tmp.values[e].SD != undefined) {
                            var sortKey = me.tmp.values[e].SD;
                            if (me.tmp.values.length > 1) {
                                var sortValue = eval("me.sortData(minData, maxData, me.tmp.values[e][" + measure + "], me.tmp.values[e].SD)");
                                resultDataList[sortKey] = sortValue
                            } else {
                                resultDataList[sortKey] = 1
                            }
                        }
                    }

                    me.callGeo(me.tmp, resultDataList)
                })
            },

            emdCall: function (item, resultDataList) {
                var me = this
                var url = 'http://localhost:8090/emdgeo/search/findBySdCodeAndSigCodeAndName?sdcode=' + me.geoCodeList[item.SD].code + '&sigcode=' + me.geoCodeList[item.SD].children[item.SIG].code + '&emdname=' + item.EMD;

                return new Promise((resolve, reject) => {
                    process.nextTick(() => {
                        $.ajax({
                            type: "get",
                            url: url,
                            contentType: "application/hal+json; charset=UTF-8",
                            success: function (data) {

                                var emd_kor_na = data._embedded.emdgeo[0].name
                                var geoData = data._embedded.emdgeo[0].geom

                                me.drawPolygon(geoData, resultDataList[emd_kor_na])
                                resolve()
                            }
                        })
                    })
                });
                //
                // $.ajax({
                //     type: "get",
                //     url: url,
                //     contentType: "application/hal+json; charset=UTF-8",
                //     success: function (data) {
                //         var emd_kor_na = data._embedded.emdgeo[0].name
                //         var geoData = data._embedded.emdgeo[0].geom
                //         var convertData = me.convertGeom(geoData)
                //
                //         // me.drawPolygon(convertData, resultDataList[emd_kor_na])
                //     }
                // });
            },
            sigCall: function (item, resultDataList) {
                var me = this
                var url = 'http://localhost:8090/siggeo/search/findBySdCodeAndName?sdcode=' + me.geoCodeList[item.SD].code + '&name=' + item.SIG;
                return new Promise((resolve, reject) => {
                    process.nextTick(() => {
                        $.ajax({
                            type: "get",
                            url: url,
                            contentType: "application/hal+json; charset=UTF-8",
                            success: function (data) {

                                var sig_kor_na = data._embedded.siggeo[0].name;
                                var geoData = data._embedded.siggeo[0].geom;
                                // console.log(data._embedded.sdgeo[0]._links.self.href.split('/'))

                                // var sdCode = data._embedded.sdgeo[0]._links.self.href.split('/')[data._embedded.sdgeo[0]._links.self.href.split('/').length - 1]
                                // console.log("=================")
                                // console.log(sd_kor_na)
                                // console.log(geoData)
                                // console.log("=================")
                                me.drawPolygon(geoData, resultDataList[sig_kor_na])
                                resolve()
                            }
                        })
                    })
                });

                // $.ajax({
                //     type: "get",
                //     url: url,
                //     contentType: "application/hal+json; charset=UTF-8",
                //     success: function (data) {
                //         var sig_kor_na = data._embedded.siggeo[0].name
                //         var geoData = data._embedded.siggeo[0].geom
                //         // var convertData = me.convertGeom(geoData)
                //         console.log(geoData)
                //
                //         if (me.chart == 'Map') {
                //             me.drawPolygon(geoData, resultDataList[sig_kor_na])
                //         }
                //     }
                // });
            },
            sdCall: function (item, resultDataList) {
                var me = this
                var url = 'http://localhost:8090/sdgeo/search/findByName?name=' + item.SD

                return new Promise((resolve, reject) => {
                    process.nextTick(() => {
                        $.ajax({
                            type: "get",
                            url: url,
                            contentType: "application/hal+json; charset=UTF-8",
                            success: function (data) {

                                var sd_kor_na = data._embedded.sdgeo[0].name;
                                var geoData = data._embedded.sdgeo[0].geom;
                                // console.log(data._embedded.sdgeo[0]._links.self.href.split('/'))

                                // var sdCode = data._embedded.sdgeo[0]._links.self.href.split('/')[data._embedded.sdgeo[0]._links.self.href.split('/').length - 1]
                                // console.log("=================")
                                // console.log(sd_kor_na)
                                // console.log(geoData)
                                // console.log("=================")
                                me.drawPolygon(geoData, resultDataList[sd_kor_na])
                                resolve()
                            }
                        })
                    })
                });


            },
            async callGeo(MDXdata, resultDataList) {
                var me = this;


                for (var i = 0; i < MDXdata.values.length; i++) {
                    if (MDXdata.values[i].EMD != null) {
                        await me.emdCall(MDXdata.values[i], resultDataList)
                    } else if (MDXdata.values[i].SIG != null) {
                        await me.sigCall(MDXdata.values[i], resultDataList)
                    } else if (MDXdata.values[i].SIG == null && MDXdata.values[i].EMD == null && MDXdata.values[i].SD) {
                        await me.sdCall(MDXdata.values[i], resultDataList)
                    }
                }


            },

            getAddress: function () {
                var me = this
                return new Promise(function (resolve, reject) {
                    $.ajax({
                        type: "get",
                        url: 'http://api.vworld.kr/req/address?service=address&request=getAddress&key=D01A2D82-B1E6-3340-84D4-462002F06506&crs=EPSG:5186&format=json&point=' + me.x + ',' + me.y + '&type=both',
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
                // console.log(resultData)
                var format = new ol.format.WKT();
                // console.log(format)
                if (resultData == 0) {
                    // console.log(resultData)
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

                var feature = format.readFeature(wkt);
                // console.log(wkt)

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
