<script>
    import { onMount } from 'svelte';
    import { getLocalUser, getUser } from '../clients/User_client';
    export let activeView;
    export let setActiveView;
    
    let loggedInUser = getLocalUser();
    let gamesPlayed = [];

    /** @param {String} username */
    async function retrieveUser(username){
        const retrievedUser = await getUser(username);
        if (retrievedUser){
                gamesPlayed = retrievedUser.gamesPlayed;
            } else {
                    console.log('Could not retrieve user');
                }
        }

        onMount(() =>{
            retrieveUser(loggedInUser.username)
            })
</script>

<h1>Player Page for {loggedInUser.username}</h1>

{#if gamesPlayed.length > 0}
    <h2>Games played:</h2>
    <table>
        <thead>
            <tr>
                <th>WPM</th>
            </tr>
        </thead>
        <tbody>
            {#each gamesPlayed as game}
                <tr>
                    <td>{game.wpm}</td>
                </tr>
            {/each}
        </tbody>
    </table>
{:else}
    <p>No games played yet.</p>
{/if}
<br>

<button on:click={() => {localStorage.clear()
                        setActiveView('home')}}>Log out</button>

<style>
    h1 {
        margin-top: 10%;
        color: goldenrod;
        }
    button{
            color: goldenrod;
        }
    table {
        width: 100%;
        border-collapse: collapse;
    }
    th, td {
        padding: 8px 12px;
        border: 1px solid #ddd;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
        color: black;
    }
</style>

