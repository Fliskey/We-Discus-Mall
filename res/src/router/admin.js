/**
 * @authors Your Name (you@example.org)
 * @date    2021-07-02 15:29:16
 * @version $Id$ 管理员登录
 */
import layout from '@/page/layout'
export default [
  {
    path: '/admin',
    component: layout,
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
        path: 'user/list',
        component: () => import('@/views/user/list')
      },
      {
        path: 'goods/list',
        component: () => import('@/views/goods/list')
      },
      {
        path: 'goods/detail',
        component: () => import('@/views/goods/edit')
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
      }
    ]
  }
]
