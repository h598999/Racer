<script>
    import Menu from "./Menu.svelte";
    export let activeView;
    export let setActiveView;

</script>

<Menu {activeView} {setActiveView} />

<button on:click={setActiveView('SPGame')}> SinglePlayer </button>
<br>
<br>
<button> MultiPlayer </button>
