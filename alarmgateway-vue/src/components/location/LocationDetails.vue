<template>
  <div class="row">
    <div class="col-lg-12">
      <h4>Location details</h4>
      <h4>Customer</h4>
      <div>
        <label>id:</label>
        {{this.p_location.id}}
      </div>
      <div>
        <label>street:</label>
        {{this.p_location.street}}
      </div>
      <div>
        <label>streetNumber:</label>
        {{this.p_location.streetNumber}}
      </div>
      <div>
        <label>postalCode:</label>
        {{this.p_location.postalCode}}
      </div>
      <div>
        <label>phoneNumber:</label>
        {{this.p_location.town}}
      </div>
      <div>
        <label>street:</label>
        {{this.p_location.phoneNumber}}
      </div>
      <div>
        <label>label:</label>
        {{this.p_location.label}}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["p_location"]
};
</script>