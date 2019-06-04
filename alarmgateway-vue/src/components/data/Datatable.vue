<template>
  <div>
    <h1>The Home Page Dt</h1>
    <hr>
    <p>
      Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab asperiores, aut autem, blanditiis consequuntur
      dolore excepturi laborum maiores minima nihil non nulla obcaecati quas quibusdam quod sed suscipit vero
      voluptatem.
    </p>
    <div class="form-group">
      <label>current page</label>
      <input class="form-control" type="text" v-model="currentPage">
    </div>
    <button @click="currentPage++" class="btn btn-primary">next</button>
    <div class="form-group">
      <label>page size</label>
      <input class="form-control" type="text" v-model="perPage">
    </div>
    <button class="btn btn-danger" @click="retrieveGateways()">refresh</button>
    <table class="table">
      <thead>
        <tr>
          <th>id</th>
          <th>mac</th>
          <th>view|edit|delete</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(gateway, index) in gateways" :key="index">
          <td>{{gateway.id}}</td>
          <td>{{gateway.mac}}</td>
          <td>
            <router-link
              class="btn btn-info"
              :to="{ 
                  name: 'gateway-details',
                  params: { 
                    alarmgateway: gateway, 
                    id: gateway.id 
                    }
                  }"
            >view</router-link>
            <router-link
              class="btn btn-warning"
              :to="{ 
                  name: 'gateway-edit',
                  params: { 
                    alarmgateway: gateway, 
                    id: gateway.id 
                    }
                  }"
            >edit</router-link>
            <button class="btn btn-danger" @click="deleteAlarmgateway(gateway.id)">delete</button>
          </td>
        </tr>
      </tbody>
    </table>
    
    <hr>

    <v-data-table
      :headers="headers"
      :items="gateways"
      :pagination.sync="pagination"
      :total-items="total"
      :rows-per-page-items="rowsPerPageItems"
      :loading="loading"
    >      <template v-slot:items="props">
        <td>{{ props.item.id }}</td>
        <td class="text-xs-left">{{ props.item.mac }}</td>
      </template></v-data-table>
        <hr>
  <p>{{pagination}}</p>
  <hr>
  </div>


</template>

<script>
export default {
  name: "alarmgatewayList",
  data() {
    return {
      gateways: [],
      headers: [{ text: "id", value: "ID" }, { text: "mac", value: "MAC" }],
      search: "",
      total: 0,
      loading: false,
      pagination: {},
      rowsPerPageItems: [5, 10, 20, 50, 100]
    };
  },
  
  methods: {
    /* eslint-disable no-console */
    retrieveGateways() {
      this.$http
        .get("alarm/get?page=" + this.pagination.page + "&size=" + this.pagination.rowsPerPage)
        .then(res => {
          return res.json();
        })
        .then(data => {
          console.log(data);
          this.total = data.totalPages;
          //this.gateways = data;
          const resultArray = [];
          for (let key in data.content) {
            resultArray.push(data.content[key]);
          }
          this.gateways = resultArray;
        });
    },

    refreshList() {
      this.retrieveGateways();
    },
    deleteAlarmgateway(id) {
      this.$http.delete("alarm/" + id).then(response => {
        console.log("response:" + response),
          this.retrieveGateways(),
          error => {
            console.log("delete error:" + error);
          };
      });
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.retrieveGateways();
  },
  watch: {
    currentPage() {
      this.retrieveGateways();
    },
    pagination() {
            this.retrieveGateways();
        },
    search() {
            this.retrieveGateways();
        }
  },
  computed: {
    rows() {
      return this.items.length;
    }
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