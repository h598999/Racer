
<script>
    import Menu from "./Menu.svelte";
    import { registerUser } from "../helpermethotds/user_client.js";
    export let activeView;
    export let setActiveView;

    let username = '';
    let password = '';
    let repeatPassword = '';

    /**
    *@param {String} username 
    *@param {String} password 
    *@param {String} repeatPassword
    */
    async function register(username, password, repeatPassword){
        if (username == null || password == null || repeatPassword == null){
            alert("Fill in all the fields")
        } else if(password !== repeatPassword){
            alert("Password and repeat password must be equal")
        }
        const response = await registerUser(username, password);
        if (response){
            setActiveView('login')
        } else {
            alert("Error!")
        }
    }

</script>

<style>
 #Input{
     justify-content: center;
     background-color: #e6dbda;
     color: black;
     border-top-left-radius: 10px;
     border-bottom-left-radius: 10px;
     border-top-right-radius: 10px;
     border-bottom-right-radius: 10px;
     padding-bottom: 100px;
   }
  input{
      border-color: darkgoldenrod;
      background-color: #e6dbda;
      color: black;
      width: 300px;
      height: 40;
      padding: 10px;
      border-top-left-radius: 10px;
      border-bottom-left-radius: 10px;
      border-top-right-radius: 10px;
      border-bottom-right-radius: 10px;
    }

  button{
      background-color: darkgoldenrod;
      margin-top: 10px;
    }

  button:hover{
      background-color: grey;
      margin-top: 10px;
    }

</style>

<Menu {activeView} {setActiveView}/>

<div id="Input">
  <h3>Login: </h3>
   Brukernavn: <br>
   <input type="text" placeholder="Username:" bind:value={username}> 
   <br>
   Passord:    <br>
   <input type="password" placeholder="Password:" bind:value={password}> 
   <br>
   Gjenta Passord: <br>
   <input type="password" placeholder="Repeat password:" bind:value={repeatPassword}> 
   <br>
   <button on:click={()=> register(username,password,repeatPassword)}>Registrer</button>
</div>
