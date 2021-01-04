import Vue from 'vue'
import createPersistedState from 'vuex-persistedstate'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  plugins: [createPersistedState()], // 设置全局变量为可储存的插件,防止页面刷新后全局变量初始化
  state: {
    storeCurrentClassNo: '',
    storeCurrentCourseName: '',
    storeCurrentClassName: '',
    storeCurrentRegisterTime: '',
    termInfo: {
      clickChildrenTerm: '2017-2018-1',
      clickTerm: '2017-2018',
      termList: [{
        termValue: '2017-2018学年',
        termKey: '2017-2018',
        termChildren: [{value: '2017-2018学年第一学期', termChildrenKey: '2017-2018-1'}, {value: '2017-2018学年第二学期', termChildrenKey: '2017-2018-2'}]
      },
      {
        termValue: '2018-2019学年',
        termKey: '2018-2019',
        termChildren: [{value: '2018-2019学年第一学期', termChildrenKey: '2018-2019-1'}, {value: '2018-2019学年第二学期', termChildrenKey: '2018-2019-2'}]
      },
      {
        termValue: '2019-2020学年',
        termKey: '2019-2020',
        termChildren: [{value: '2019-2020学年第一学期', termChildrenKey: '2019-2020-1'}, {value: '2019-2020学年第二学期', termChildrenKey: '2019-2020-2'}]
      },
      {
        termValue: '2020-2021学年',
        termKey: '2020-2021',
        termChildren: [{value: '2020-2021学年第一学期', termChildrenKey: '2020-2021-1'}, {value: '2020-2021学年第二学期', termChildrenKey: '2020-2021-2'}]
      }]}// 学期信息

    // 这里放全局参数
  },

  mutations: {

    /**
     * 学期信息为全局变量，更新信息
     * @param state
     * @param info
     */
    setTermInfo (state, info) {
      state.termInfo = info
    },
    /**
     * 设置学期信息为全局变量，设置学期为当前选择学期
     * @param state
     * @param termInfo
     */
    setClickChildrenTerm (state, termInfo) {
      state.termInfo.clickTerm = termInfo.clickTerm
      state.termInfo.clickChildrenTerm = termInfo.clickChildrenTerm
    },
    setStoreCurrentClassNo (state, classNo) {
      state.storeCurrentClassNo = classNo
    },
    setStoreCurrentCourseName (state, courseName) {
      state.storeCurrentCourseName = courseName
    },
    setStoreCurrentClassName (state, className) {
      state.storeCurrentClassName = className
    },
    setStoreCurrentRegisterTime (state, time) {
      state.storeCurrentRegisterTime = time
    }
    // 这里是set方法
  },

  getters: {
    // 这里是get方法
    /**
     * 返回学期信息
     * @param state
     * @returns {{termList: [{termKey: string, termValue: string, termChildren: [{termChildrenKey: string, value: string}, {termChildrenKey: string, value: string}]}, {termKey: string, termValue: string, termChildren: [{termChildrenKey: string, value: string}, {termChildrenKey: string, value: string}]}, {termKey: string, termValue: string, termChildren: [{termChildrenKey: string, value: string}, {termChildrenKey: string, value: string}]}, {termKey: string, termValue: string, termChildren: [{termChildrenKey: string, value: string}, {termChildrenKey: string, value: string}]}], clickTerm: string}|{termList: [{termKey: string, termValue: string, termChildren: [{termChildrenKey: string, value: string}, {termChildrenKey: string, value: string}]}, {termKey: string, termValue: string, termChildren: [{termChildrenKey: string, value: string}, {termChildrenKey: string, value: string}]}, {termKey: string, termValue: string, termChildren: [{termChildrenKey: string, value: string}, {termChildrenKey: string, value: string}]}, {termKey: string, termValue: string, termChildren: [{termChildrenKey: string, value: string}, {termChildrenKey: string, value: string}]}], clickTerm: string}|default.data.$store.getters.getTermInfo|[{term: string, termClick: number, termChildren: [{termKey: string, click: number, value: string}, {termKey: string, click: number, value: string}]}, {term: string, termClick: number, termChildren: [{termKey: string, click: number, value: string}, {termKey: string, click: number, value: string}]}, {term: string, termClick: number, termChildren: [{termKey: string, click: number, value: string}, {termKey: string, click: number, value: string}]}, {term: string, termClick: number, termChildren: [{termKey: string, click: number, value: string}, {termKey: string, click: number, value: string}]}]}
     */
    getTermInfo (state) {
      return state.termInfo
    }
  },

  actions: {

    // 这个部分我暂时用不上

  },

  modules: {

    // 这里是我自己理解的是为了给全局变量分组，所以需要写提前声明其他store文件，然后引入这里

  }

})
