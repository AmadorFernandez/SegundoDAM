using UnityEngine;
using System.Collections;

public class SeguirBola : MonoBehaviour {


    public GameObject bola;
    Vector3 distancia;

	// Use this for initialization
	void Start () {

        distancia = transform.position;
	}
	
	// Update is called once per frame
	void Update () {

        transform.position = new Vector3(bola.transform.position.x, distancia.y+bola.transform.position.y, bola.transform.position.z+distancia.z);

    }
}
