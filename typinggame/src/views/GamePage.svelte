<script>
    import Menu from "./Menu.svelte";
    import SpGame from "../components/SPGame.svelte";
    export let activeView;
    export let setActiveView;


    
</script>

<Menu {activeView} {setActiveView} />

<SpGame />
