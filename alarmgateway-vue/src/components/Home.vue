<template>
  <div>
    <h1>The Home Page</h1>
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
    <div class="overflow-auto">
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
    ></b-pagination>

    <p class="mt-3">Current Page: {{ currentPage }}</p>

    <b-table
      id="my-table"
      :items="items"
      :per-page="perPage"
      :current-page="currentPage"
      small
    ></b-table>
  </div>
  </div>
</template>

<script>
export default {
  name: "alarmgatewayList",
  data() {
    return {
      gateways: [],
        perPage: 3,
        currentPage: 1,
        items: [
          { id: 1, first_name: 'Fred', last_name: 'Flintstone' },
          { id: 2, first_name: 'Wilma', last_name: 'Flintstone' },
          { id: 3, first_name: 'Barney', last_name: 'Rubble' },
          { id: 4, first_name: 'Betty', last_name: 'Rubble' },
          { id: 5, first_name: 'Pebbles', last_name: 'Flintstone' },
          { id: 6, first_name: 'Bamm Bamm', last_name: 'Rubble' },
          { id: 7, first_name: 'The Great', last_name: 'Gazzoo' },
          { id: 8, first_name: 'Mr', last_name: 'Slate' },
          { id: 9, first_name: 'Pearl', last_name: 'Slaghoople' }
        ]
    };
  },
  methods: {
    /* eslint-disable no-console */
    retrieveGateways() {
      this.$http
        .get("alarm/get?page=" + this.currentPage + "&size=" + this.perPage)
        .then(res => {
          return res.json();
        })
        .then(data => {
          console.log(data);
          this.totalPages = data.totalPages;
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
    },
    /* eslint-enable no-console */
  },
  mounted() {
    this.retrieveGateways();
  },
  watch:{
      currentPage(){
          this.retrieveGateways();
      }
  },
  computed: {
      rows() {
        return this.items.length;
      },
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