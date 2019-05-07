import Vue from 'vue'
import Router from 'vue-router'
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

Vue.use(Router)

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
    }
  ]
})
