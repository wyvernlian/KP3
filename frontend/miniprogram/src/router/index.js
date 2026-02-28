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
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue'),
    meta: {
      title: '登录'
    }
  },
  {
    path: '/personal-booking',
    name: 'PersonalBooking',
    component: () => import('../views/PersonalBooking.vue'),
    meta: {
      title: '个人预约'
    }
  },
  {
    path: '/team-booking',
    name: 'TeamBooking',
    component: () => import('../views/TeamBooking.vue'),
    meta: {
      title: '团队预约'
    }
  },
  {
    path: '/activity-booking',
    name: 'ActivityBooking',
    component: () => import('../views/ActivityBooking.vue'),
    meta: {
      title: '活动预约'
    }
  },
  {
    path: '/venues',
    name: 'Venues',
    component: () => import('../views/Venues.vue'),
    meta: {
      title: '线上场馆'
    }
  },
  {
    path: '/venue-detail',
    name: 'VenueDetail',
    component: () => import('../views/VenueDetail.vue'),
    meta: {
      title: '场馆详情'
    }
  },
  {
    path: '/info',
    name: 'Info',
    component: () => import('../views/Info.vue'),
    meta: {
      title: '科普资讯'
    }
  },
  {
    path: '/info-detail',
    name: 'InfoDetail',
    component: () => import('../views/InfoDetail.vue'),
    meta: {
      title: '资讯详情'
    }
  },
  {
    path: '/my',
    name: 'My',
    component: () => import('../views/My.vue'),
    meta: {
      title: '个人中心'
    }
  },
  {
    path: '/profile',
    name: 'Profile',
    component: () => import('../views/Profile.vue'),
    meta: {
      title: '个人信息'
    }
  },
  {
    path: '/booking-history',
    name: 'BookingHistory',
    component: () => import('../views/BookingHistory.vue'),
    meta: {
      title: '预约历史'
    }
  },
  {
    path: '/friends',
    name: 'Friends',
    component: () => import('../views/Friends.vue'),
    meta: {
      title: '亲友信息'
    }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  document.title = to.meta.title || '科普科技馆预约'
  next()
})

export default router