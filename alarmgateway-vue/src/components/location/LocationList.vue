<template>
  <div class="row">
    <div class="col-lg-12">
      <h4>Location List</h4>
          <router-link tag="button" to="/location/add-location" class="btn btn-primary" ><span class="glyphicon glyphicon-plus"></span> Add location</router-link>
      <table class="table">
        <thead>
          <tr>
            <th>id</th>
            <th>strreet</th>
            <th>nr</th>
            <th>town</th>
            <th>postal code</th>
            <th>phone number</th>
            <th>phone number</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="location in locations">
            <td>{{location.id}}</td>
            <td>{{location.street}}</td>
            <td>{{location.streetNumber}}</td>
            <td>{{location.town}}</td>
            <td>{{location.postalCode}}</td>
            <td>{{location.phoneNumber}}</td>
            <td>              <router-link
                class="btn btn-info"
                :to="{ 
                  name: 'location-details',
                  params: { 
                      id:location.id,
                    p_location: location
                    }
                  }"
              >view</router-link>
              <router-link
                class="btn btn-warning"
                :to="{ 
                  name: 'location-edit',
                  params: { 
                      id:location.id,
                    p_location: location
                    }
                  }"
              >edit</router-link>
              <button class="btn btn-danger" @click="deleteLocation(location.id)">delete</button>
            </td>
          </tr>
        </tbody>
      </table>
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
  methods: {
    /* eslint-disable no-console */
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
    },
      deleteLocation(id) {
      this.$http
      .delete("location/" + id)
      .then(response=>{
        console.log("response:" + response),
        this.getLocations(),
        error=>{
          console.log("delete error:" + error);
        }
      });
    }
  },

    /* eslint-enable no-console */
  mounted() {
    this.getLocations();
  }
};
</script>
