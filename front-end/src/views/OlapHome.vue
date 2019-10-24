<template>
  <div>
    <v-row>
      <v-col style="margin: 10px;">
        <olap v-on:delete-row="deleteThisRow(index)"
              :componentWidth.sync="items[0].size"
              :containerWidth="containerWidth"
              :Id="items[0].id"
              v-bind:mode.sync="mode"
              :tmp.sync="tmp"
              :columnNav.sync="columnNav"
              :filterNav.sync="filterNav"
              :rowsNav.sync="rowsNav"
        >
        </olap>
      </v-col>
    </v-row>
  </div>
</template>

<script>
    /* eslint-disable */

    import Olap from './olap.vue'

    export default {
        components: {
            'olap': Olap,
        },
        props: {
            componentWidth : Number,
            mode: String,
            columnNav: Array,
            rowsNav: Array,
            filterNav: Array,
            tmp: Object
        },
        computed: {

        },
        created () {
            var me = this
            if(me.tmp != undefined) {
                me.tmp = {}
            }
        },

        watch: {
            containerHeight(newHeight, oldHeight) {
                var me = this
                me.containerHeight = newHeight;
            },

        },
        mounted () {
            var me = this
        },

        data () {
            return {
                items: [
                    {
                        id: 1,
                        size: 900,
                    },
                ],
                containerHeight: 0,
                containerWidth: 0,
                selected: null,
            }
        },

        methods: {
            deleteThisRow: function(index) {
                var me = this
                me.items.splice(index, 1);
            }
        }
    }
</script>

<style lang="scss" scoped>

</style>
<style scoped>



</style>
