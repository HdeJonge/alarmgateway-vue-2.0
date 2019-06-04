<template>
  <div class="row">
    <div class="col-lg-12">
      <h4>Add location</h4>
      <div v-if="errors.length>0" class="alert alert-danger" role="alert">
        <ul>
          <li v-for="error in errors">{{error.field + " " + error.defaultMessage}}</li>
        </ul>
      </div>
      <div class="form-group">
        <label>street</label>
        <input class="form-control" type="text" v-model="location.street">
      </div>
      <div class="form-group">
        <label>streetNumber</label>
        <input class="form-control" type="text" v-model="location.streetNumber">
      </div>
      <div class="form-group">
        <label>postalCode</label>
        <input class="form-control" type="text" v-model="location.postalCode">
      </div>
      <div class="form-group">
        <label>town</label>
        <input class="form-control" type="text" v-model="location.town">
      </div>
      <div class="form-group">
        <label>phoneNumber</label>
        <input class="form-control" type="text" v-model="location.phoneNumber">
      </div>
      <div class="form-group">
        <label>label</label>
        <select class="form-control" v-model="location.labelList" multiple>
          <option v-for="lb in labels" :value="lb">{{lb.description}}</option>
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
      location: {
        id: 0,
        street: "",
        streetNumber: "",
        postalCode: "",
        town: "",
        phoneNumber: "",
        labelList: []
      },
      selected: [],
      resource: {},
      labels: [],
      errors: []
    };
  },
  methods: {
    saveData() {
      this.resource
        .save(this.location)
        .then(response => {
          console.log(response),
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
    getLabels() {
      this.$http
        .get("label")
        .then(res => {
          return res.json();
        })
        .then(data => {
          console.log(data);
          const result = [];
          for (let key in data) {
            result.push(data[key]);
          }
          this.labels = result;
        })
        .catch(error => {
          this.error = error;
        });
    },
    return() {
      this.$router.push("/location");
    }
  },
  created() {
    this.resource = this.$resource("location/");
  },
  mounted() {
    this.getLabels();
  }
};
</script>