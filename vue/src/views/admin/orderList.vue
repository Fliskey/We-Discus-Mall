<template>
  <div>
    <div class="table-operations">
      <a-button @click="clearAll">
        Clear filters and sorters
      </a-button>
    </div>
    <a-table :columns="columns" :data-source="data" @change="handleChange">
      <span slot="State" slot-scope="State">
      <a-tag
        v-for="st in State"
        :key="st"
        :color="st.toString() === '已付款' ? 'volcano' : st==='已发货' ? 'geekblue' : 'green'"
      >
        {{st}}
      </a-tag>
    </span>
      <div
        slot="filterDropdown"
        slot-scope="{ setSelectedKeys, selectedKeys, confirm, clearFilters, column }"
        style="padding: 8px"
      >
        <a-input
          v-ant-ref="c => (searchInput = c)"
          :placeholder="`Search ${column.dataIndex}`"
          :value="selectedKeys[0]"
          style="width: 188px; margin-bottom: 8px; display: block;"
          @change="e => setSelectedKeys(e.target.value ? [e.target.value] : [])"
          @pressEnter="() => handleSearch(selectedKeys, confirm, column.dataIndex)"
        />
        <a-button
          type="primary"
          icon="search"
          size="small"
          style="width: 90px; margin-right: 8px"
          @click="() => handleSearch(selectedKeys, confirm, column.dataIndex)"
        >
          Search
        </a-button>
        <a-button size="small" style="width: 90px" @click="() => handleReset(clearFilters)">
          Reset
        </a-button>
      </div>
      <a-icon
        slot="filterIcon"
        slot-scope="filtered"
        type="search"
        :style="{ color: filtered ? '#108ee9' : undefined }"
      />
      <template slot="customRender" slot-scope="text, record, index, column">
      <span v-if="searchText && searchedColumn === column.dataIndex">
        <template
          v-for="(fragment, i) in text
            .toString()
            .split(new RegExp(`(?<=${searchText})|(?=${searchText})`, 'i'))"
        >
          <mark
            v-if="fragment.toLowerCase() === searchText.toLowerCase()"
            :key="i"
            class="highlight"
          >{{ fragment }}</mark
          >
          <template v-else>{{ fragment }}</template>
        </template>
      </span>
        <template v-else>
          {{ text }}
        </template>
      </template>
    </a-table>
  </div>
</template>
<script>
  const columns =[
    {
      title: '订单号',
      dataIndex: 'id',
      width: '20%',
      key: 'id',
      scopedSlots: {
        filterDropdown: 'filterDropdown',
        filterIcon: 'filterIcon',
        customRender: 'customRender',
      },
      onFilter: (value, record) =>
        record.id
          .toString()
          .toLowerCase()
          .includes(value.toLowerCase()),
      onFilterDropdownVisibleChange: visible => {
        if (visible) {
          setTimeout(() => {
            this.searchInput.focus();
          }, 0);
        }
      },
    },
    {
      title: '买家ID',
      dataIndex: 'BuyerId',
      width: '20%',
      key: 'BuyerId',
      scopedSlots: {
        filterDropdown: 'filterDropdown',
        filterIcon: 'filterIcon',
        customRender: 'customRender',
      },
      onFilter: (value, record) =>
        record.BuyerId
          .toString()
          .toLowerCase()
          .includes(value.toLowerCase()),
      onFilterDropdownVisibleChange: visible => {
        if (visible) {
          setTimeout(() => {
            this.searchInput.focus();
          });
        }
      },
    },
    {
      title: '卖家ID',
      dataIndex: 'SellerId',
      width: '20%',
      key: 'SellerId',
      scopedSlots: {
        filterDropdown: 'filterDropdown',
        filterIcon: 'filterIcon',
        customRender: 'customRender',
      },
      onFilter: (value, record) =>
        record.SellerId
          .toString()
          .toLowerCase()
          .includes(value.toLowerCase()),
      onFilterDropdownVisibleChange: visible => {
        if (visible) {
          setTimeout(() => {
            this.searchInput.focus();
          });
        }
      },
    },{
    title: '状态',
      dataIndex: 'State',
      width: '40%',
      key: 'State',
      filters: [
        {
          text: '已付款',
          value: '已付款',
        },
        {
          text: '已发货',
          value: '已发货',
        },
        {
          text: '已收货',
          value: '已收货'
        }
      ],
      filterMultiple: false,
      onFilter: (value, record) => record.State.indexOf(value) === 0,
      sortDirections: ['descend', 'ascend'],
      scopedSlots: {customRender: 'State'}
    }
  ]

  const data = [
    {
      key: '1',
      id: '1',
      BuyerId: '1',
      SellerId: '1',
      State: ['已付款'],
    },
    {
      key: '2',
      id: '2',
      BuyerId: '2',
      SellerId: '2',
      State: ['已发货'],
    },
    {
      key: '2',
      id: '2',
      BuyerId: '2',
      SellerId: '2',
      State: ['已收货'],
    }
  ];

  export default {
    data() {
      return {
        data,
        columns,
        filteredInfo: null,
        sortedInfo: null,
      };
    },
    computed: {
    },
    methods: {
      handleSearch(selectedKeys, confirm, dataIndex) {
        confirm();
        this.searchText = selectedKeys[0];
        this.searchedColumn = dataIndex;
      },
      handleReset(clearFilters) {
        clearFilters();
        this.searchText = '';
      },
      handleChange(pagination, filters, sorter) {
        console.log('Various parameters', pagination, filters, sorter);
        this.filteredInfo = filters;
        this.sortedInfo = sorter;
      },
      clearAll() {
        this.filteredInfo = null;
        this.sortedInfo = null;
      },
    },
  };
</script>
<style scoped>
.table-operations {
  margin-bottom: 16px;
}

.table-operations > button {
  margin-right: 8px;
}
</style>
