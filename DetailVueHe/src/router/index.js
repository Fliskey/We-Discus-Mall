import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Table from '../views/Table.vue'
import Edit from '../views/Edit.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Main from '../views/Main.vue'
import Message from '../views/Message.vue'
import Detail from "../views/Detail.vue";
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/table',
    name: 'Table',
    component: Table
  },
  {
    path: '/Detail',
    name:'Detail',
    component: Detail
  },
  {
    path: '/edit',
    name: 'Edit',
    component: Edit
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/main',
    name: 'Main',
    component: Main
  },
  {
    path: '/message',
    name: 'Message',
    component: Message
  },
  {
    path: '/about',
    name: 'About',
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
