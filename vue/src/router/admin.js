/**
 * @authors Fliskey
 * @date    2021-07-07 19:17:18
 * @version $Id$ 管理员登录
 */
import admin_layout from '@/page/admin_layout'
export default [
  {
    path: '/admin',
    component: admin_layout,
    children: [
      {
        path: 'list',
        component: () => import('@/views/admin/list')
      },
      {
        path: 'home/home',
        component: () => import('@/views/home/home')
      },
      {
        path: 'user/editMessage',
        component: () => import('@/views/admin/editMessage')
      }
    ]
  }
]
