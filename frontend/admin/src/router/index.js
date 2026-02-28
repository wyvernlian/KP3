import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import('../views/Home.vue'),
    meta: {
      title: '首页'
    }
  },
  {
    path: '/system',
    name: 'System',
    component: () => import('../views/System.vue'),
    meta: {
      title: '系统管理'
    },
    children: [
      {
        path: 'user',
        name: 'User',
        component: () => import('../views/system/User.vue'),
        meta: {
          title: '用户管理'
        }
      },
      {
        path: 'role',
        name: 'Role',
        component: () => import('../views/system/Role.vue'),
        meta: {
          title: '角色管理'
        }
      },
      {
        path: 'menu',
        name: 'Menu',
        component: () => import('../views/system/Menu.vue'),
        meta: {
          title: '菜单管理'
        }
      }
    ]
  },
  {
    path: '/organization',
    name: 'Organization',
    component: () => import('../views/Organization.vue'),
    meta: {
      title: '运营机构管理'
    }
  },
  {
    path: '/venue',
    name: 'Venue',
    component: () => import('../views/Venue.vue'),
    meta: {
      title: '场馆和活动管理'
    }
  },
  {
    path: '/booking',
    name: 'Booking',
    component: () => import('../views/Booking.vue'),
    meta: {
      title: '预约管理'
    }
  },
  {
    path: '/info',
    name: 'Info',
    component: () => import('../views/Info.vue'),
    meta: {
      title: '科普资讯管理'
    }
  },
  {
    path: '/analysis',
    name: 'Analysis',
    component: () => import('../views/Analysis.vue'),
    meta: {
      title: '数据分析'
    }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  if (!token && to.path !== '/login') {
    next('/login')
  } else {
    next()
  }
})

export default router