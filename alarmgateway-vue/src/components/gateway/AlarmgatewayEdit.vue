
<template>
  <div class="row">
    <div class="col-lg-12">
      <h4>Edit gateway</h4>
      <div class="form-group">
        <label>mac|{{alarmgateway.id}}</label>
        <input class="form-control" type="text" v-model="alarmgateway.mac">
      </div>
      <div class="form-group">
        <label>settings profile</label>
        <select class="form-control" v-model="alarmgateway.settingsProfile">
          <option value="A">a</option>
          <option value="B">b</option>
          <option value="C">c</option>
        </select>
      </div>
      <div class="form-group">
        <label>location</label>
        <select class="form-control" v-model="alarmgateway.location">
          <option v-for="loc in locations" :value="loc">{{loc.locationDescription}}</option>
        </select>
      </div>
      <button v-on:click="updateData" class="btn btn-success">Update</button>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      locations: []
    };
  },
  name: "alarmgateway",
  props: ["alarmgateway"],
  methods: {
    updateData() {
      this.$http
        .put("alarm/" + this.alarmgateway.id, this.alarmgateway)
        .then(response => {
          this.return();
          console.log(response.data),
            error => {
              console.log("put error: " + error);
            };
        });
    },
    return() {
      this.$router.push("/gateway");
      //this.$router.push({path:'/'});
    },
    getLocations() {
      this.$http
        .get("location")
        .then(res => {
          return res.json();
        })
        .then(data => {
          console.log("retreived data: " + data);
          const result = [];
          for (let key in data) {
            result.push(data[key]);
          }
          this.locations = result;
        });
    }
  },
  mounted() {
    this.getLocations();
  }
};
</script>