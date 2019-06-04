<template>
  <div class="row">
    <div class="col-lg-12">
      <h4>New gateway</h4>
      <div v-if="errors.length>0" class="alert alert-danger" role="alert">
        <ul>
          <li v-for="error in errors">{{error.field + " " + error.defaultMessage}}</li>
        </ul>
      </div>
      <div class="form-group">
        <label>mac</label>
        <input class="form-control" type="text" v-model="gateway.mac">
      </div>
      <div class="form-group">
        <label>profile</label>
        <select class="form-control" v-model="gateway.settingsProfile">
          <!-- <option value="">nothing selected</option> -->
          <option value="1">A</option>
          <option value="2">B</option>
          <option value="3">C</option>
        </select>
      </div>
      <div class="form-group">
        <label>location</label>
        <select class="form-control" v-model="gateway.location">
          <!-- <option value=null selected>nothing selected</option> -->
          <option v-for="loc in locations" :value="loc">{{loc.locationDescription}}</option>
        </select>
      </div>
      <button v-on:click="saveData" class="btn btn-success">Submit</button>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      gateway: {
        id: 0,
        mac: "",
        settingsProfile: null,
        location: null
      },
      resource: {},
      locations: [],
      errors: []
    };
  },
  methods: {
    saveData() {
      this.resource
        .save(this.gateway)
        .then(response => {
          console.log("save response: " + response),
            error => {
              console.log(error);
            };
          this.return();
        })
        .catch(error => {
          console.log(error);
          this.errors = error.body.errors;
        });
    },
    getLocations() {
      this.$http
        .get("location")
        .then(res => {
          return res.json();
        })
        .then(data => {
          const result = [];
          for (let key in data) {
            result.push(data[key]);
          }
          this.locations = result;
        });
    },
    return() {
      this.$router.push("/gateway");
    }
  },
  created() {
    this.resource = this.$resource("alarm/");
  },
  mounted() {
    this.getLocations();
  }
};
</script>