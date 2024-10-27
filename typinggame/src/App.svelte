<script>
    import GamePage from "./views/GamePage.svelte";
    import LoginPage from "./views/loginPage.svelte";
    import ModeSelector from "./views/mode-selector.svelte";
    import RegPage from "./views/RegPage.svelte";
    import WelcomeBox from "./views/welcome-box.svelte";
    import PlayerPage from "./views/PlayerPage.svelte";
    import Menu from "./views/Menu.svelte";

    function isLoggedIn(){
            return sessionStorage.getItem('User') !== null
        }

let activeView = 'home'; //This is the default view

/** @param {String} view */
function setActiveView(view){
  activeView = view;
}

</script>

<Menu { activeView } {setActiveView}/>
{#if activeView == 'home'}
<WelcomeBox {activeView} {setActiveView}/>
{:else if activeView == 'login'}
    {#if isLoggedIn() == true}
    <PlayerPage {activeView} {setActiveView}/>
    {:else}
    <LoginPage {activeView} {setActiveView}/>
    {/if}
{:else if activeView == 'selector'}
<ModeSelector {activeView} {setActiveView} />
{:else if activeView == 'SPGame'}
<GamePage {activeView} {setActiveView} />
{:else if activeView == 'register'}
<RegPage {activeView} {setActiveView} />
{/if}
