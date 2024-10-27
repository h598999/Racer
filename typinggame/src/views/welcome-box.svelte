<script>
  import Menu from "./Menu.svelte";
  export let activeView;
  export let setActiveView;

</script>
<style>
  h1{
      color: darkgoldenrod;
    }
  button{
      color: darkgoldenrod;
    }
</style>

<Menu { activeView } {setActiveView}/>

<h1>Typing Game</h1>
<p> Hi and welcome to the best typinggame you will ever play! </p>
<p> If you login you can save your score and play against other players </p>
<p> If you want to play as a guest you can play our offline modes </p>


<button on:click={setActiveView('selector')}>Select mode</button> <br>
<br>
<button>LogIn</button>
