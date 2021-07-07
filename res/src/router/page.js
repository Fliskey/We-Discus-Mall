/**
 * @authors Your Name (you@example.org)
 * @date    2021-06-09 18:09:04
 * @version $Id$
 */
export default [
  {
    path: '/login',
    component: () =>
      import('@/page/login/index'),
    meta: {
      isTab: false,
      isAuth: false
    }
  },
  {
    path: '*',
    redirect: '/login'
  }
]
