<template>
  <div class="row">
    <div class="col-lg-12">
      <h4>Gateway List</h4>
      <router-link tag="button" to="/gateway/add-gateway" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus"></span> Add alarm gateway
      </router-link>
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
    </div>
    <div class="col-md-6">
      <router-view @refreshData="refreshList"></router-view>
    </div>
  </div>
</template>
 
<script>
export default {
  name: "alarmgatewayList",
  data() {
    return {
      gateways: [],

    };
  },
  methods: {
    /* eslint-disable no-console */
    retrieveGateways() {
      this.$http
        .get("alarm")
        .then(res => {
          return res.json();
        })
        .then(data => {
          console.log(data);
          //this.gateways = data;
          const resultArray = [];
          for (let key in data) {
            resultArray.push(data[key]);
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