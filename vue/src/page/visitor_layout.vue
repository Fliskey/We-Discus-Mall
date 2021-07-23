<template>
  <a-layout id="components-layout-demo-custom-trigger" style='height:100%'>
    <a-layout-sider v-model="collapsed" :trigger="null" collapsible>
      <div class="logo">二手交易市场</div>
      <a-menu theme="dark" mode="inline" :default-selected-keys="[$route.path]" @select='jumpTo'>
        <a-menu-item key="/visitor/goods/list">
          <a-icon type="shop" />
          <span>集贸市场</span>
        </a-menu-item>
        <a-menu-item key="/visitor/goods/public">
          <a-icon type="plus-square" />
          <span>商品发布</span>
        </a-menu-item>
        <a-menu-item key="/visitor/goods/reserve">
          <a-icon type="star" />
          <span>我的预定</span>
        </a-menu-item>
        <a-menu-item key="/visitor/goods/trolley">
          <a-icon type="shopping-cart" />
          <span>购物车</span>
        </a-menu-item>
        <a-menu-item key="/visitor/user/sell">
          <a-icon type="user" />
          <span>我的发布</span>
        </a-menu-item>
        <a-menu-item key="/visitor/user/sellOut">
          <a-icon type="shopping" />
          <span>我的卖出</span>
        </a-menu-item>
        <a-sub-menu key="sub1">
          <span slot="title"><a-icon type="user" /><span>个人中心</span></span>
          <a-menu-item-group key="g1">
            <a-menu-item key="/visitor/center/info">个人信息</a-menu-item>
            <a-menu-item key="/visitor/center/psw">修改密码</a-menu-item>
          </a-menu-item-group>
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header class='header'>
        <a-row type='flex' align='middle' justify='space-between'>
          <a-icon
            class="trigger"
            :type="collapsed ? 'menu-unfold' : 'menu-fold'"
            @click="() => (collapsed = !collapsed)"
          />
          <a-menu v-model="current" mode="horizontal" :default-selected-keys="[$route.path]" @click='jumpTo'>
            <a-menu-item key="/visitor/home/home"> <a-icon type="home" />
              首页
            </a-menu-item>
            <a-sub-menu>
            </a-sub-menu>
            <a-menu-item key="alipay">
              <a href="https://antdv.com" target="_blank" rel="noopener noreferrer"
              >帮助</a
              >
            </a-menu-item>
          </a-menu>
          <span @click='loginout'><a-icon type="poweroff" />&nbsp;&nbsp;&nbsp;&nbsp;登出</span>
        </a-row>
      </a-layout-header>
      <a-layout-content
        :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: 'initial' }"
      >
        <router-view />
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script>
  export default {
    data () {
      return {
        collapsed: false
      }
    },
    methods: {
      loginout () {
        this.$confirm({
          title: '是否确认退出?',
          okText: '确认',
          cancelText: '取消',
          onOk: () => {
            this.$cookies.remove('vid')
            this.$router.push({
              path: '/login'
            })
          },
          onCancel () {}
        })
      },
      jumpTo (item) {
        this.$router.push({
          path: item.key
        })
      }
    }
  }
</script>
<style lang='less'>
  .header.ant-layout-header {
    padding: 0 20px;
    cursor: pointer;
    transition: color 0.3s;
    background:#fff;
    .anticon{
      font-size: 18px;
    }
  }

  #components-layout-demo-custom-trigger .trigger:hover {
    color: #1890ff;
  }

  #components-layout-demo-custom-trigger .logo {
    height: 60px;
    background: rgba(255, 255, 255, 0.2);
    text-align: center;
    line-height: 60px;
    color: #fff;
    font-weight: 700;
    font-size: 20px;
    letter-spacing: 2px;
  }
</style>
