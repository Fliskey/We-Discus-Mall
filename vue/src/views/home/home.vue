<template>
  <div>
    <a-carousel class="slick-slide" autoplay>
      <div><h3 class="slick-slide">1</h3></div>
      <div><h3>2</h3></div>
      <div><h3>3</h3></div>
      <div><h3>4</h3></div>
    </a-carousel>
    <a-row>
      <a-col :span="8">
        <a-card hoverable style="width: 300px">
          <img
            slot="cover"
            alt="example"
            src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
          />
          <template slot="actions" class="ant-card-actions">
            <a-icon key="setting" type="setting" />
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
            src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
          />
          <template slot="actions" class="ant-card-actions">
            <a-icon key="setting" type="setting" />
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
            src="public/img/dd.jpg"
          />
          <template slot="actions" class="ant-card-actions">
            <a-icon key="setting" type="setting" />
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
              www.instagram.com
            </template>
          </a-card-meta>
        </a-card>
      </a-col>
      <a-col :span="6">
        <a-card hoverable style="width: 240px">
          <img
            slot="cover"
            alt="example"
            src="dd.jpg"
          />
          <a-card-meta title="Europe Street beat">
            <template slot="description">
              www.instagram.com
            </template>
          </a-card-meta>
        </a-card>
      </a-col>
      <a-col :span="6">
        <a-card hoverable style="width: 240px">
          <img
            slot="cover"
            alt="example"
            src="dd.jpg"
          />
          <a-card-meta title="Europe Street beat">
            <template slot="description">
              www.instagram.com
            </template>
          </a-card-meta>
        </a-card>
      </a-col>
      <a-col :span="6">
        <a-card hoverable style="width: 240px">
          <img
            slot="cover"
            alt="example"
            src="dd.jpg"
          />
          <a-card-meta title="Europe Street beat">
            <template slot="description">
              www.instagram.com
            </template>
          </a-card-meta>
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>

<script>
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
      onChange (a, b, c) {
        console.log(a, b, c)
      }
    }
  }
</script>

<style scoped>
/* For demo */
.ant-carousel >>> .slick-slide div{
  text-align: center;
  height: 160px;
  line-height: 160px;
  background: #364d79;
  overflow: hidden;
}

.ant-carousel >>> .slick-slide h3 {
  color: #fff;
}
</style>
