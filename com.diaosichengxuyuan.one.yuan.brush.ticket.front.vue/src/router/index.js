import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '@/components/homePage'
import TicketSearch from '@/components/ticketSearch'
import TicketSearchResult from '@/components/ticketSearchResult'

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
    }
  ]
})
