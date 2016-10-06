using UnityEngine;
using System.Collections;

public class MoverBola : MonoBehaviour {


    float velocidad = 2f;
    public float altura = 8F;
    Rigidbody rb;

    void Start()
    {

         rb = GetComponent<Rigidbody>();

    }

	// Update is called once per frame
	void Update () {

        float vGiro = 30f;
        transform.Translate(0,0,Input.GetAxis("Profundidad") * Time.deltaTime * velocidad);
        transform.Translate(Input.GetAxis("Horizontal") * Time.deltaTime * velocidad,0, 0 );
        //    transform.Rotate(0,0 , Input.GetAxis("Horizontal") * Time.deltaTime * vGiro*-1);
        

        if(Input.GetAxis("Profundidad") != 0)
        {

           

        }

        if (Input.GetKey(KeyCode.Space) &&  transform.position.y <= 0.59)
        {
            rb.AddForce(new Vector3(0, altura, 0), ForceMode.Impulse);

        }
     

        











    }
}
