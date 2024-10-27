<!-- Menu.svelte -->
<script>
  export let activeView;
  export let setActiveView;

  function isLoggedIn(){
          return sessionStorage.getItem('User') !== null
  }
</script>
<nav>
  <ul>
    <li><button on:click={() => setActiveView('home')}>Home</button></li>
    {#if isLoggedIn()}
        <li><button on:click={() => setActiveView('login')}>My page</button></li>
    {:else}
        <li><button on:click={() => setActiveView('login')}>Log in</button></li>
    {/if}
    <li></li>
  </ul>
</nav>

<style>
  li button:hover{
      background-color: darkgoldenrod;
    }
  nav {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    background-color: #333;
    padding: 1rem;
    z-index: 1000; /* Keeps it above other content */
    display: flex;
    justify-content: flex-end;
  }

  ul {
    list-style-type: none;
    display: flex;
    gap: 1rem;
    margin: 0;
    padding: 0;
    margin-left: auto;
  }
</style>

