<template>
  <div>
    <a-carousel class="slick-slide" autoplay>
      <div><img width="100%" height="100%" src='/img/fruit.jpg' alt="加载失败"></div>
      <div><img width="100%" height="100%" src="/img/graduation.jpg"></div>
      <div><img width="100%" height="100%" src='/img/recommend.jpg'></div>
      <div><img width="100%" height="100%" src='/img/list.jpg'></div>
    </a-carousel>
    <br />
    <a-row>
      <a-col :span="8">
        <a-card hoverable style="width: 300px">
          <img
            slot="cover"
            alt="example"
            src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
          />
          <template slot="actions" class="ant-card-actions">
            <a-icon key="setting" type="heart" />
            <a-icon key="edit" type="edit" />
            <a-icon key="ellipsis" type="ellipsis" />
          </template>
          <a-card-meta title="收购电音蝌蚪一只" description="租赁也可以">
            <a-avatar
              slot="avatar"
              src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
            />
          </a-card-meta>
        </a-card>
      </a-col>
      <a-col :span="8">

        <a-card hoverable style="width: 300px">
          <img
            slot="cover"
            alt="example"
            src='img/dd.jpg'
          />
          <template slot="actions" class="ant-card-actions">
            <a-icon key="setting" type="heart" />
            <a-icon key="edit" type="edit" />
            <a-icon key="ellipsis" type="ellipsis" />
          </template>
          <a-card-meta title="收购电音蝌蚪一只" description="租赁也可以">
            <a-avatar
              slot="avatar"
              src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
            />
          </a-card-meta>
        </a-card>
      </a-col>
      <a-col :span="8">

        <a-card hoverable style="width: 300px">
          <img
            slot="cover"
            alt="example"
            src="/img/dd.jpg"
          />
          <template slot="actions" class="ant-card-actions">
            <a-icon key="setting" type="heart" />
            <a-icon key="edit" type="edit" />
            <a-icon key="ellipsis" type="ellipsis" />
          </template>
          <a-card-meta title="收购电音蝌蚪一只" description="租赁也可以">
            <a-avatar
              slot="avatar"
              src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
            />
          </a-card-meta>
        </a-card>
      </a-col>
    </a-row>
    <br />
    <a-list :grid="{ gutter: 16, xs: 1, sm: 2, md: 4, lg: 4, xl: 6, xxl: 3 }" :data-source="data">
      <a-list-item slot="renderItem" slot-scope="item, index" @click="jumpToSearch">
        <a-card :title="item.title">
        </a-card>
      </a-list-item>
    </a-list>
    <a-row>
      <a-col :span="6">
        <a-card hoverable style="width: 240px">
          <img
            slot="cover"
            alt="example"
            src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
          />
          <a-card-meta title="Europe Street beat">
            <template slot="description">
            </template>
          </a-card-meta>
        </a-card>
      </a-col>
      <a-col :span="6">
        <a-card hoverable style="width: 240px">
          <img
            slot="cover"
            alt="example"
            src="/img/dd.jpg"
          />
          <a-card-meta title="Europe Street beat">
            <template slot="description">
            </template>
          </a-card-meta>
        </a-card>
      </a-col>
      <a-col :span="6">
        <a-card hoverable style="width: 240px">
          <img
            slot="cover"
            alt="example"
            src="/img/dd.jpg"
          />
          <a-card-meta title="Europe Street beat">
            <template slot="description">
            </template>
          </a-card-meta>
        </a-card>
      </a-col>
      <a-col :span="6">
        <a-card hoverable style="width: 240px">
          <img
            slot="cover"
            alt="example"
            src="/img/dd.jpg"
          />
          <a-card-meta title="Europe Street beat">
            <template slot="description">
            </template>
          </a-card-meta>
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>

<script>
const data = [
  {
    title: '零食',
  },
  {
    title: '电子产品',
  },
  {
    title: '票卷',
  },
  {
    title: '生活用品',
  },
  {
    title: '书籍',
  },
  {
    title: '学习用品',
  },
];

const listData = [];
for (let i = 0; i < 23; i++) {
  listData.push({
    href: 'https://www.antdv.com/',
    title: `ant design vue part ${i}`,
    avatar: 'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png',
    description:
      'Ant Design, a design language for background applications, is refined by Ant UED Team.',
    content:
      'We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
  });
}

export default {
  data() {
    return {
      data,
    };
  },
  name: 'home',
  listData,
  pagination: {
    onChange: page => {
      console.log(page)
    },
    pageSize: 3
  },
  actions: [
    { type: 'star-o', text: '156' },
    { type: 'like-o', text: '156' },
    { type: 'message', text: '2' }
  ],
  methods: {
    //验证cookies这怎么能删呢？by@Fliskey
    mounted() {
      if(this.$cookies.isKey('vid') === false)
        this.$router.push('login')
    },
    onChange (a, b, c) {
      console.log(a, b, c)
    },
    jumpToSearch(item){
      alert("你点击了"+item.title);
    }
  }
}
</script>

<style scoped>
/* For demo */
.ant-carousel >>> .slick-slide div{
  text-align: center;
  height: 300px;
  line-height: 160px;
  background: #364d79;
  overflow: hidden;
}

.ant-carousel >>> .slick-slide h3 {
  color: #fff;
}
</style>
