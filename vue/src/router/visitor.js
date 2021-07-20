/**
 * @authors Your Name (you@example.org)
 * @date    2021-07-02 15:29:16
 * @version $Id$ 管理员登录
 */
import visitor_layout from '@/page/visitor_layout'
export default [
  {
    path: '/visitor',
    component: visitor_layout,
    children: [
      {
        path: 'center/psw',
        component: () => import('@/views/center/psw')
      },
      {
        path: 'center/info',
        component: () => import('@/views/center/info')
      },
      {
        path: 'goods/list',
        component: () => import('@/views/goods/list')
      },
      {
        path: 'goods/detail/:id',
        component: () => import('@/views/goods/detail')
      },
      {
        path: 'goods/public',
        component: () => import('@/views/goods/public')
      },
      {
        path: 'home/home',
        component: () => import('@/views/home/home')
      },
      {
        path: 'user/sell',
        component: () => import('@/views/user/sell')
      },
      {
        path: 'goods/trolley',
        component: () => import('@/views/goods/trolley')
      },
      {
        path: 'goods/reserve',
        component: () => import('@/views/goods/reserve')
      },
      {
        path: 'goods/change/:id',
        component: () => import('@/views/goods/change')
      },
      {
        path: 'goods/purchase/:id',
        component: ()=> import('@/views/goods/purchase')
      },
      {
        path: 'goods/settlement',
        component: ()=> import('@/views/goods/settlement')
      },
      {
        path: 'user/sellOut',
        component: ()=> import('@/views/user/sellOut')
      },
      {
        path: 'goods/pay/:gid/:aid',
        component: ()=> import('@/views/goods/pay')
      }
    ]
  }
]
