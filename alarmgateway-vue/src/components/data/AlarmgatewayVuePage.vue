<template>
  <div>
    <h1>The Home Page</h1>
    <hr>
    <p>
      Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab asperiores, aut autem, blanditiis consequuntur
      dolore excepturi laborum maiores minima nihil non nulla obcaecati quas quibusdam quod sed suscipit vero
      voluptatem.
    </p>
    <v-text-field @input="retrieveGateways" label="search" v-model="search"></v-text-field>
    <v-data-table
      :headers="headers"
      :items="gateways"
      :rows-per-page-items="rowsPerPageItems"
      :pagination.sync="pagination"
      :total-items="total"
      :loading="loading"
      class="elevation-1"
    >
      <template v-slot:items="props">
        <td>{{ props.item.id }}</td>
        <td class="text-xs-left">{{ props.item.mac }}</td>
        <td>
          <router-link
            class="btn btn-info"
            :to="{ 
                  name: 'gateway-details',
                  params: { 
                    alarmgateway: props.item, 
                    id: props.item.id 
                    }
                  }"
          >view</router-link>
          <router-link
            class="btn btn-warning"
            :to="{ 
                  name: 'gateway-edit',
                  params: { 
                    alarmgateway: props.item, 
                    id: props.item.id 
                    }
                  }"
          >edit</router-link>
          <button class="btn btn-danger" @click="deleteAlarmgateway(gateway.id)">delete</button>
        </td>
      </template>
    </v-data-table>
  </div>
</template>

<script>
export default {
  name: "datable1",
  data() {
    return {
      resource: {},
      total: 100,
      columns: ["id", "mac"],
      desserts: [],
      gateways: [],
      search: "",
      loading: true,
      pagination: { rowsPerPage: 10 },
      rowsPerPageItems: [5, 10, 20, 50, 100],
      headers: [
        { text: "id", value: "id" },
        { text: "mac", value: "mac" },
        { text: "view/edit/delete" }
      ]
    };
  },
  watch: {
    pagination: {
      handler() {
        this.retrieveGateways();
      },
      deep: true
    }
  },
  mounted() {
    this.retrieveGateways();
  },
  methods: {
    retrieveGateways() {
      this.resource
        .get({
          query: {
            search: this.search,
            desc: this.pagination.descending,
            sortBy: this.pagination.sortBy,
            page: this.pagination.page,
            size: this.pagination.rowsPerPage,
            columns: ["id", "mac"]
          }
        })
        .then(res => {
          return res.json();
        })
        .then(data => {
          this.total = data.totalElements;
          const resultArray = [];
          for (let key in data.content) {
            resultArray.push(data.content[key]);
          }
          this.gateways = resultArray;
        });
      setTimeout(() => {
        this.loading = false;
      }, 500);
    }
  },
  created() {
    this.resource = this.$resource("page/get{?query*}");
  }
};
</script>
 
<style>
.list {
  text-align: left;
  max-width: 450px;
  margin: auto;
}
</style>