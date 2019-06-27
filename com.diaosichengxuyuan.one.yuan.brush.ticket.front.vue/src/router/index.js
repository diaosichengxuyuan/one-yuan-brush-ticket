import Vue from 'vue'
import Router from 'vue-router'
import vueResource from 'vue-resource'
import VeeValidate from 'vee-validate'
import zh_CN from 'vee-validate/dist/locale/zh_CN'
import VueI18n from 'vue-i18n'
import HomePage from '@/components/homePage'
import TicketSearch from '@/components/ticketSearch'
import TicketSearchResult from '@/components/ticketSearchResult'
import AddBrushTicketTask from '@/components/addBrushTicketTask'
import BrushTicketTaskList from '@/components/brushTicketTaskList'
import BrushTicketTaskListStopped from '@/components/brushTicketTaskListStopped'
import BrushTicketTaskListFinished from '@/components/brushTicketTaskListFinished'
import BrushTicketTaskListStarted from '@/components/brushTicketTaskListStarted'
import GotTicketList from '@/components/gotTicketList'
import GotTicketListNotPay from '@/components/gotTicketListNotPay'
import GotTicketListHasPay from '@/components/gotTicketListHasPay'
import GotTicketListCancelled from '@/components/gotTicketListCancelled'
import Login from '@/components/login'
import LoginSuccess from '@/components/loginSuccess'
import Member from '@/components/member'

//路由组件
Vue.use(Router);
//http请求组件
Vue.use(vueResource);
//校验国际化
Vue.use(VueI18n);
const i18n = new VueI18n({
  locale: 'zh_CN'
});
//校验器
Vue.use(VeeValidate, {
  i18n,
  i18nRootKey: 'validation',
  dictionary: {
    zh_CN
  }
});
//允许跨域
Vue.http.options.emulateJSON = true;
Vue.http.options.crossOrigin = true;
Vue.http.options.emulateHTTP = true;
//携带cookie
Vue.http.options.credentials = true;

export default new Router({
  mode: "history",
  routes: [
    {
      path: '/',
      name: 'RootPage',
      component: HomePage
    },
    {
      path: '/homePage',
      name: 'HomePage',
      component: HomePage
    },
    {
      path: '/ticketSearch',
      name: 'TicketSearch',
      component: TicketSearch
    },
    {
      path: '/ticketSearchResult',
      name: 'TicketSearchResult',
      component: TicketSearchResult
    },
    {
      path: '/addBrushTicketTask',
      name: 'AddBrushTicketTask',
      component: AddBrushTicketTask
    },
    {
      path: '/brushTicketTaskList',
      name: 'BrushTicketTaskList',
      component: BrushTicketTaskList
    },
    {
      path: '/brushTicketTaskListStopped',
      name: 'BrushTicketTaskListStopped',
      component: BrushTicketTaskListStopped
    },
    {
      path: '/brushTicketTaskListFinished',
      name: 'BrushTicketTaskListFinished',
      component: BrushTicketTaskListFinished
    },
    {
      path: '/brushTicketTaskListStarted',
      name: 'BrushTicketTaskListStarted',
      component: BrushTicketTaskListStarted
    },
    {
      path: '/gotTicketList',
      name: 'GotTicketList',
      component: GotTicketList
    },
    {
      path: '/gotTicketListNotPay',
      name: 'GotTicketListNotPay',
      component: GotTicketListNotPay
    },
    {
      path: '/gotTicketListHasPay',
      name: 'GotTicketListHasPay',
      component: GotTicketListHasPay
    },
    {
      path: '/gotTicketListCancelled',
      name: 'GotTicketListCancelled',
      component: GotTicketListCancelled
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/loginSuccess',
      name: 'LoginSuccess',
      component: LoginSuccess
    },
    {
      path: '/member',
      name: 'Member',
      component: Member
    }
  ]
})
