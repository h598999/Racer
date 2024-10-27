const baseUrl = 'http://localhost:8080/api/v2/words/';

export async function getWords(){
  const response = await fetch(baseUrl + 'ten')
  return await response.json();
}


