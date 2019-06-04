
<template>
  <div class="row">
    <div class="col-lg-12">
      <h4>Edit location</h4>
      <div class="form-group">
        <label>Street</label>
        <input class="form-control" type="text" v-model="p_location.street">
      </div>
      <div class="form-group">
        <label>Streeet number</label>
        <input class="form-control" type="text" v-model="p_location.streetNumber">
      </div>
      <div class="form-group">
        <label>post code</label>
        <input class="form-control" type="text" v-model="p_location.postalCode">
      </div>
      <div class="form-group">
        <label>phone number</label>
        <input class="form-control" type="text" v-model="p_location.phoneNumber">
      </div>
      <div class="form-group">
        <label>town</label>
        <input class="form-control" type="text" v-model="p_location.town">
      </div>
      <div class="form-group">
        <label>label</label>
        <select class="form-control" v-model="p_location.labelList" multiple>
          <option v-for="lb in labels" :value="lb">{{lb.description}}</option>
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
      labels: []
    };
  },
  props: ["p_location"],
  methods: {
    updateData() {
      this.$http
        .put("location/" + this.p_location.id, this.p_location)
        .then(response => {
          this.return();
          console.log(response.data),
            error => {
              console.log("put error: " + error);
            };
        });
    },
    return() {
      this.$router.push("/location");
      //this.$router.push({path:'/'});
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
        });
    }
  },
  mounted() {
    this.getLabels();
  }
};
</script>