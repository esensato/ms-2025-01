import type {
  OpenAPIClient,
  Parameters,
  UnknownParamsObject,
  OperationResponse,
  AxiosRequestConfig,
} from 'openapi-client-axios';

declare namespace Components {
    namespace Schemas {
        export interface AbstractJsonSchemaPropertyObject {
            title?: string;
            readOnly?: boolean;
        }
        /**
         * Representa um aluno
         */
        export interface Aluno {
            id?: number; // int32
            /**
             * Nome do aluno
             * example:
             * Joao Pereira
             */
            nome: string;
            turma: string;
            curso?: string;
        }
        export interface CollectionModelEntityModelDisciplinaBean {
            _embedded?: {
                disciplinaBeans?: EntityModelDisciplinaBean[];
            };
            _links?: Links;
        }
        export interface CollectionModelEntityModelMatriculaBean {
            _embedded?: {
                matriculaBeans?: EntityModelMatriculaBean[];
            };
            _links?: Links;
        }
        export interface DisciplinaBean {
            id?: number; // int32
            nome?: string;
            cargaHoraria?: number; // int32
        }
        export interface DisciplinaBeanRequestBody {
            id?: number; // int32
            nome?: string;
            cargaHoraria?: number; // int32
        }
        export interface DisciplinasAlunoBean {
            aluno?: /* Representa um aluno */ Aluno;
            disciplinas?: DisciplinaBean[];
        }
        export interface EntityModelDisciplinaBean {
            id?: number; // int32
            nome?: string;
            cargaHoraria?: number; // int32
            _links?: Links;
        }
        export interface EntityModelMatriculaBean {
            id?: number; // int32
            idAluno?: number; // int32
            idDisciplina?: number; // int32
            status?: string;
            _links?: Links;
        }
        export interface Item {
            type?: string;
            properties?: {
                [name: string]: AbstractJsonSchemaPropertyObject;
            };
            requiredProperties?: string[];
        }
        export interface JsonSchema {
            title?: string;
            description?: string;
            properties?: {
                [name: string]: AbstractJsonSchemaPropertyObject;
            };
            requiredProperties?: string[];
            definitions?: {
                [name: string]: Item;
            };
            type?: string;
            $schema?: string;
        }
        export interface Link {
            href?: string;
            hreflang?: string;
            title?: string;
            type?: string;
            deprecation?: string;
            profile?: string;
            name?: string;
            templated?: boolean;
        }
        export interface Links {
            [name: string]: Link;
        }
        export interface MatriculaBean {
            id?: number; // int32
            idAluno?: number; // int32
            idDisciplina?: number; // int32
            status?: string;
        }
        export interface MatriculaBeanRequestBody {
            id?: number; // int32
            idAluno?: number; // int32
            idDisciplina?: number; // int32
            status?: string;
        }
        export interface RepresentationModelObject {
            _links?: Links;
        }
    }
}
declare namespace Paths {
    namespace Atualizar {
        namespace Parameters {
            export type Id = number; // int32
        }
        export interface PathParameters {
            id: Parameters.Id /* int32 */;
        }
        export type RequestBody = /* Representa um aluno */ Components.Schemas.Aluno;
        namespace Responses {
            export type $200 = string;
            export interface $400 {
                [name: string]: string;
            }
            export interface $404 {
            }
        }
    }
    namespace Cadastrar {
        export type RequestBody = /* Representa um aluno */ Components.Schemas.Aluno;
        namespace Responses {
            export type $200 = number; // int32
            export interface $400 {
                [name: string]: string;
            }
            export interface $404 {
            }
        }
    }
    namespace Cancelar {
        namespace Parameters {
            export type IdAluno = number; // int32
            export type IdDisciplina = number; // int32
        }
        export interface PathParameters {
            idAluno: Parameters.IdAluno /* int32 */;
            idDisciplina: Parameters.IdDisciplina /* int32 */;
        }
        namespace Responses {
            export type $200 = Components.Schemas.MatriculaBean;
            export interface $400 {
            }
            export interface $404 {
            }
        }
    }
    namespace CargaHoraria {
        namespace Parameters {
            export type IdAluno = number; // int32
        }
        export interface PathParameters {
            idAluno: Parameters.IdAluno /* int32 */;
        }
        namespace Responses {
            export type $200 = number; // int32
            export interface $400 {
            }
            export interface $404 {
            }
        }
    }
    namespace DeleteItemResourceDisciplinabeanDelete {
        namespace Parameters {
            export type Id = string;
        }
        export interface PathParameters {
            id: Parameters.Id;
        }
        namespace Responses {
            export interface $204 {
            }
            export interface $404 {
            }
        }
    }
    namespace DeleteItemResourceMatriculabeanDelete {
        namespace Parameters {
            export type Id = string;
        }
        export interface PathParameters {
            id: Parameters.Id;
        }
        namespace Responses {
            export interface $204 {
            }
            export interface $404 {
            }
        }
    }
    namespace Descriptor111 {
        namespace Responses {
            export type $200 = string;
        }
    }
    namespace Descriptor112 {
        namespace Responses {
            export type $200 = string;
        }
    }
    namespace DisciplinasAluno {
        namespace Parameters {
            export type IdAluno = number; // int32
        }
        export interface PathParameters {
            idAluno: Parameters.IdAluno /* int32 */;
        }
        namespace Responses {
            export type $200 = Components.Schemas.DisciplinasAlunoBean;
            export interface $400 {
            }
            export interface $404 {
            }
        }
    }
    namespace ExecuteSearchMatriculabeanGet {
        namespace Parameters {
            export type IdAluno = number; // int32
            export type IdDisciplina = number; // int32
        }
        export interface QueryParameters {
            idAluno?: Parameters.IdAluno /* int32 */;
            idDisciplina?: Parameters.IdDisciplina /* int32 */;
        }
        namespace Responses {
            export type $200 = Components.Schemas.EntityModelMatriculaBean;
            export interface $404 {
            }
        }
    }
    namespace ExecuteSearchMatriculabeanGet1 {
        namespace Parameters {
            export type IdAluno = number; // int32
        }
        export interface QueryParameters {
            idAluno?: Parameters.IdAluno /* int32 */;
        }
        namespace Responses {
            export type $200 = Components.Schemas.CollectionModelEntityModelMatriculaBean;
            export interface $404 {
            }
        }
    }
    namespace ExecuteSearchMatriculabeanGet2 {
        namespace Parameters {
            export type IdDisciplina = number; // int32
        }
        export interface QueryParameters {
            idDisciplina?: Parameters.IdDisciplina /* int32 */;
        }
        namespace Responses {
            export type $200 = Components.Schemas.CollectionModelEntityModelMatriculaBean;
            export interface $404 {
            }
        }
    }
    namespace GetAluno {
        namespace Parameters {
            export type Id = number; // int32
        }
        export interface QueryParameters {
            id?: Parameters.Id /* int32 */;
        }
        namespace Responses {
            export type $200 = /* Representa um aluno */ Components.Schemas.Aluno;
            export interface $400 {
                [name: string]: string;
            }
            export interface $404 {
            }
        }
    }
    namespace GetAlunoByPath {
        namespace Parameters {
            export type Id = number; // int32
        }
        export interface PathParameters {
            id: Parameters.Id /* int32 */;
        }
        namespace Responses {
            export type $200 = /* Representa um aluno */ Components.Schemas.Aluno;
        }
    }
    namespace GetCollectionResourceDisciplinabeanGet1 {
        namespace Responses {
            export type $200 = Components.Schemas.CollectionModelEntityModelDisciplinaBean;
        }
    }
    namespace GetCollectionResourceMatriculabeanGet1 {
        namespace Responses {
            export type $200 = Components.Schemas.CollectionModelEntityModelMatriculaBean;
        }
    }
    namespace GetItemResourceDisciplinabeanGet {
        namespace Parameters {
            export type Id = string;
        }
        export interface PathParameters {
            id: Parameters.Id;
        }
        namespace Responses {
            export type $200 = Components.Schemas.EntityModelDisciplinaBean;
            export interface $404 {
            }
        }
    }
    namespace GetItemResourceMatriculabeanGet {
        namespace Parameters {
            export type Id = string;
        }
        export interface PathParameters {
            id: Parameters.Id;
        }
        namespace Responses {
            export type $200 = Components.Schemas.EntityModelMatriculaBean;
            export interface $404 {
            }
        }
    }
    namespace ListAllFormsOfMetadata1 {
        namespace Responses {
            export type $200 = Components.Schemas.RepresentationModelObject;
        }
    }
    namespace Matricular {
        namespace Parameters {
            export type IdAluno = number; // int32
            export type IdDisciplina = number; // int32
        }
        export interface PathParameters {
            idAluno: Parameters.IdAluno /* int32 */;
            idDisciplina: Parameters.IdDisciplina /* int32 */;
        }
        namespace Responses {
            export type $200 = Components.Schemas.MatriculaBean;
            export interface $400 {
            }
            export interface $404 {
            }
        }
    }
    namespace PatchItemResourceDisciplinabeanPatch {
        namespace Parameters {
            export type Id = string;
        }
        export interface PathParameters {
            id: Parameters.Id;
        }
        export type RequestBody = Components.Schemas.DisciplinaBeanRequestBody;
        namespace Responses {
            export type $200 = Components.Schemas.EntityModelDisciplinaBean;
            export interface $204 {
            }
        }
    }
    namespace PatchItemResourceMatriculabeanPatch {
        namespace Parameters {
            export type Id = string;
        }
        export interface PathParameters {
            id: Parameters.Id;
        }
        export type RequestBody = Components.Schemas.MatriculaBeanRequestBody;
        namespace Responses {
            export type $200 = Components.Schemas.EntityModelMatriculaBean;
            export interface $204 {
            }
        }
    }
    namespace PostCollectionResourceDisciplinabeanPost {
        export type RequestBody = Components.Schemas.DisciplinaBeanRequestBody;
        namespace Responses {
            export type $201 = Components.Schemas.EntityModelDisciplinaBean;
        }
    }
    namespace PostCollectionResourceMatriculabeanPost {
        export type RequestBody = Components.Schemas.MatriculaBeanRequestBody;
        namespace Responses {
            export type $201 = Components.Schemas.EntityModelMatriculaBean;
        }
    }
    namespace PutItemResourceDisciplinabeanPut {
        namespace Parameters {
            export type Id = string;
        }
        export interface PathParameters {
            id: Parameters.Id;
        }
        export type RequestBody = Components.Schemas.DisciplinaBeanRequestBody;
        namespace Responses {
            export type $200 = Components.Schemas.EntityModelDisciplinaBean;
            export type $201 = Components.Schemas.EntityModelDisciplinaBean;
            export interface $204 {
            }
        }
    }
    namespace PutItemResourceMatriculabeanPut {
        namespace Parameters {
            export type Id = string;
        }
        export interface PathParameters {
            id: Parameters.Id;
        }
        export type RequestBody = Components.Schemas.MatriculaBeanRequestBody;
        namespace Responses {
            export type $200 = Components.Schemas.EntityModelMatriculaBean;
            export type $201 = Components.Schemas.EntityModelMatriculaBean;
            export interface $204 {
            }
        }
    }
    namespace Remover {
        namespace Parameters {
            export type Id = number; // int32
        }
        export interface PathParameters {
            id: Parameters.Id /* int32 */;
        }
        namespace Responses {
            export type $200 = string;
            export interface $400 {
                [name: string]: string;
            }
            export interface $404 {
            }
        }
    }
    namespace RemoverDisciplina {
        namespace Parameters {
            export type IdDisciplina = number; // int32
        }
        export interface PathParameters {
            idDisciplina: Parameters.IdDisciplina /* int32 */;
        }
        namespace Responses {
            export type $200 = string;
            export interface $400 {
            }
            export interface $404 {
            }
        }
    }
    namespace TodosAlunos {
        namespace Responses {
            export interface $200 {
            }
            export interface $400 {
                [name: string]: string;
            }
            export interface $404 {
            }
        }
    }
    namespace TodosAlunosPorCurso {
        namespace Parameters {
            export type Curso = string;
        }
        export interface PathParameters {
            curso: Parameters.Curso;
        }
        namespace Responses {
            export interface $200 {
            }
            export interface $400 {
                [name: string]: string;
            }
            export interface $404 {
            }
        }
    }
    namespace TodosAlunosPorCursoTurma {
        namespace Parameters {
            export type Curso = string;
            export type Turma = string;
        }
        export interface PathParameters {
            curso: Parameters.Curso;
            turma: Parameters.Turma;
        }
        namespace Responses {
            export interface $200 {
            }
            export interface $400 {
                [name: string]: string;
            }
            export interface $404 {
            }
        }
    }
    namespace TodosAlunosPorNome {
        namespace Parameters {
            export type Nome = string;
        }
        export interface PathParameters {
            nome: Parameters.Nome;
        }
        namespace Responses {
            export interface $200 {
            }
            export interface $400 {
                [name: string]: string;
            }
            export interface $404 {
            }
        }
    }
}

export interface OperationMethods {
  /**
   * getCollectionResource-disciplinabean-get_1 - get-disciplinabean
   */
  'getCollectionResource-disciplinabean-get_1'(
    parameters?: Parameters<UnknownParamsObject> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.GetCollectionResourceDisciplinabeanGet1.Responses.$200>
  /**
   * postCollectionResource-disciplinabean-post - create-disciplinabean
   */
  'postCollectionResource-disciplinabean-post'(
    parameters?: Parameters<UnknownParamsObject> | null,
    data?: Paths.PostCollectionResourceDisciplinabeanPost.RequestBody,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.PostCollectionResourceDisciplinabeanPost.Responses.$201>
  /**
   * getItemResource-disciplinabean-get - get-disciplinabean
   */
  'getItemResource-disciplinabean-get'(
    parameters?: Parameters<Paths.GetItemResourceDisciplinabeanGet.PathParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.GetItemResourceDisciplinabeanGet.Responses.$200>
  /**
   * putItemResource-disciplinabean-put - update-disciplinabean
   */
  'putItemResource-disciplinabean-put'(
    parameters?: Parameters<Paths.PutItemResourceDisciplinabeanPut.PathParameters> | null,
    data?: Paths.PutItemResourceDisciplinabeanPut.RequestBody,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.PutItemResourceDisciplinabeanPut.Responses.$200 | Paths.PutItemResourceDisciplinabeanPut.Responses.$201 | Paths.PutItemResourceDisciplinabeanPut.Responses.$204>
  /**
   * patchItemResource-disciplinabean-patch - patch-disciplinabean
   */
  'patchItemResource-disciplinabean-patch'(
    parameters?: Parameters<Paths.PatchItemResourceDisciplinabeanPatch.PathParameters> | null,
    data?: Paths.PatchItemResourceDisciplinabeanPatch.RequestBody,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.PatchItemResourceDisciplinabeanPatch.Responses.$200 | Paths.PatchItemResourceDisciplinabeanPatch.Responses.$204>
  /**
   * deleteItemResource-disciplinabean-delete - delete-disciplinabean
   */
  'deleteItemResource-disciplinabean-delete'(
    parameters?: Parameters<Paths.DeleteItemResourceDisciplinabeanDelete.PathParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.DeleteItemResourceDisciplinabeanDelete.Responses.$204>
  /**
   * getCollectionResource-matriculabean-get_1 - get-matriculabean
   */
  'getCollectionResource-matriculabean-get_1'(
    parameters?: Parameters<UnknownParamsObject> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.GetCollectionResourceMatriculabeanGet1.Responses.$200>
  /**
   * postCollectionResource-matriculabean-post - create-matriculabean
   */
  'postCollectionResource-matriculabean-post'(
    parameters?: Parameters<UnknownParamsObject> | null,
    data?: Paths.PostCollectionResourceMatriculabeanPost.RequestBody,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.PostCollectionResourceMatriculabeanPost.Responses.$201>
  /**
   * executeSearch-matriculabean-get
   */
  'executeSearch-matriculabean-get'(
    parameters?: Parameters<Paths.ExecuteSearchMatriculabeanGet.QueryParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.ExecuteSearchMatriculabeanGet.Responses.$200>
  /**
   * executeSearch-matriculabean-get_1
   */
  'executeSearch-matriculabean-get_1'(
    parameters?: Parameters<Paths.ExecuteSearchMatriculabeanGet1.QueryParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.ExecuteSearchMatriculabeanGet1.Responses.$200>
  /**
   * executeSearch-matriculabean-get_2
   */
  'executeSearch-matriculabean-get_2'(
    parameters?: Parameters<Paths.ExecuteSearchMatriculabeanGet2.QueryParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.ExecuteSearchMatriculabeanGet2.Responses.$200>
  /**
   * getItemResource-matriculabean-get - get-matriculabean
   */
  'getItemResource-matriculabean-get'(
    parameters?: Parameters<Paths.GetItemResourceMatriculabeanGet.PathParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.GetItemResourceMatriculabeanGet.Responses.$200>
  /**
   * putItemResource-matriculabean-put - update-matriculabean
   */
  'putItemResource-matriculabean-put'(
    parameters?: Parameters<Paths.PutItemResourceMatriculabeanPut.PathParameters> | null,
    data?: Paths.PutItemResourceMatriculabeanPut.RequestBody,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.PutItemResourceMatriculabeanPut.Responses.$200 | Paths.PutItemResourceMatriculabeanPut.Responses.$201 | Paths.PutItemResourceMatriculabeanPut.Responses.$204>
  /**
   * patchItemResource-matriculabean-patch - patch-matriculabean
   */
  'patchItemResource-matriculabean-patch'(
    parameters?: Parameters<Paths.PatchItemResourceMatriculabeanPatch.PathParameters> | null,
    data?: Paths.PatchItemResourceMatriculabeanPatch.RequestBody,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.PatchItemResourceMatriculabeanPatch.Responses.$200 | Paths.PatchItemResourceMatriculabeanPatch.Responses.$204>
  /**
   * deleteItemResource-matriculabean-delete - delete-matriculabean
   */
  'deleteItemResource-matriculabean-delete'(
    parameters?: Parameters<Paths.DeleteItemResourceMatriculabeanDelete.PathParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.DeleteItemResourceMatriculabeanDelete.Responses.$204>
  /**
   * listAllFormsOfMetadata_1
   */
  'listAllFormsOfMetadata_1'(
    parameters?: Parameters<UnknownParamsObject> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.ListAllFormsOfMetadata1.Responses.$200>
  /**
   * descriptor_1_1_1
   */
  'descriptor_1_1_1'(
    parameters?: Parameters<UnknownParamsObject> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.Descriptor111.Responses.$200>
  /**
   * descriptor_1_1_2
   */
  'descriptor_1_1_2'(
    parameters?: Parameters<UnknownParamsObject> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.Descriptor112.Responses.$200>
  /**
   * matricular
   */
  'matricular'(
    parameters?: Parameters<Paths.Matricular.PathParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.Matricular.Responses.$200>
  /**
   * cancelar
   */
  'cancelar'(
    parameters?: Parameters<Paths.Cancelar.PathParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.Cancelar.Responses.$200>
  /**
   * getAlunoByPath - Obter dados aluno
   * 
   * Obtem os dados de um aluno pelo código
   */
  'getAlunoByPath'(
    parameters?: Parameters<Paths.GetAlunoByPath.PathParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.GetAlunoByPath.Responses.$200>
  /**
   * atualizar
   */
  'atualizar'(
    parameters?: Parameters<Paths.Atualizar.PathParameters> | null,
    data?: Paths.Atualizar.RequestBody,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.Atualizar.Responses.$200>
  /**
   * remover
   */
  'remover'(
    parameters?: Parameters<Paths.Remover.PathParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.Remover.Responses.$200>
  /**
   * getAluno
   */
  'getAluno'(
    parameters?: Parameters<Paths.GetAluno.QueryParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.GetAluno.Responses.$200>
  /**
   * cadastrar
   */
  'cadastrar'(
    parameters?: Parameters<UnknownParamsObject> | null,
    data?: Paths.Cadastrar.RequestBody,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.Cadastrar.Responses.$200>
  /**
   * disciplinasAluno
   */
  'disciplinasAluno'(
    parameters?: Parameters<Paths.DisciplinasAluno.PathParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.DisciplinasAluno.Responses.$200>
  /**
   * cargaHoraria
   */
  'cargaHoraria'(
    parameters?: Parameters<Paths.CargaHoraria.PathParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.CargaHoraria.Responses.$200>
  /**
   * todosAlunos - Lista alunos
   * 
   * Obtem a lista de todos os alunos
   */
  'todosAlunos'(
    parameters?: Parameters<UnknownParamsObject> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.TodosAlunos.Responses.$200>
  /**
   * todosAlunosPorNome
   */
  'todosAlunosPorNome'(
    parameters?: Parameters<Paths.TodosAlunosPorNome.PathParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.TodosAlunosPorNome.Responses.$200>
  /**
   * todosAlunosPorCurso
   */
  'todosAlunosPorCurso'(
    parameters?: Parameters<Paths.TodosAlunosPorCurso.PathParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.TodosAlunosPorCurso.Responses.$200>
  /**
   * todosAlunosPorCursoTurma
   */
  'todosAlunosPorCursoTurma'(
    parameters?: Parameters<Paths.TodosAlunosPorCursoTurma.PathParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.TodosAlunosPorCursoTurma.Responses.$200>
  /**
   * removerDisciplina
   */
  'removerDisciplina'(
    parameters?: Parameters<Paths.RemoverDisciplina.PathParameters> | null,
    data?: any,
    config?: AxiosRequestConfig  
  ): OperationResponse<Paths.RemoverDisciplina.Responses.$200>
}

export interface PathsDictionary {
  ['/api/disciplina']: {
    /**
     * getCollectionResource-disciplinabean-get_1 - get-disciplinabean
     */
    'get'(
      parameters?: Parameters<UnknownParamsObject> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.GetCollectionResourceDisciplinabeanGet1.Responses.$200>
    /**
     * postCollectionResource-disciplinabean-post - create-disciplinabean
     */
    'post'(
      parameters?: Parameters<UnknownParamsObject> | null,
      data?: Paths.PostCollectionResourceDisciplinabeanPost.RequestBody,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.PostCollectionResourceDisciplinabeanPost.Responses.$201>
  }
  ['/api/disciplina/{id}']: {
    /**
     * getItemResource-disciplinabean-get - get-disciplinabean
     */
    'get'(
      parameters?: Parameters<Paths.GetItemResourceDisciplinabeanGet.PathParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.GetItemResourceDisciplinabeanGet.Responses.$200>
    /**
     * putItemResource-disciplinabean-put - update-disciplinabean
     */
    'put'(
      parameters?: Parameters<Paths.PutItemResourceDisciplinabeanPut.PathParameters> | null,
      data?: Paths.PutItemResourceDisciplinabeanPut.RequestBody,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.PutItemResourceDisciplinabeanPut.Responses.$200 | Paths.PutItemResourceDisciplinabeanPut.Responses.$201 | Paths.PutItemResourceDisciplinabeanPut.Responses.$204>
    /**
     * deleteItemResource-disciplinabean-delete - delete-disciplinabean
     */
    'delete'(
      parameters?: Parameters<Paths.DeleteItemResourceDisciplinabeanDelete.PathParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.DeleteItemResourceDisciplinabeanDelete.Responses.$204>
    /**
     * patchItemResource-disciplinabean-patch - patch-disciplinabean
     */
    'patch'(
      parameters?: Parameters<Paths.PatchItemResourceDisciplinabeanPatch.PathParameters> | null,
      data?: Paths.PatchItemResourceDisciplinabeanPatch.RequestBody,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.PatchItemResourceDisciplinabeanPatch.Responses.$200 | Paths.PatchItemResourceDisciplinabeanPatch.Responses.$204>
  }
  ['/api/matricula']: {
    /**
     * getCollectionResource-matriculabean-get_1 - get-matriculabean
     */
    'get'(
      parameters?: Parameters<UnknownParamsObject> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.GetCollectionResourceMatriculabeanGet1.Responses.$200>
    /**
     * postCollectionResource-matriculabean-post - create-matriculabean
     */
    'post'(
      parameters?: Parameters<UnknownParamsObject> | null,
      data?: Paths.PostCollectionResourceMatriculabeanPost.RequestBody,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.PostCollectionResourceMatriculabeanPost.Responses.$201>
  }
  ['/api/matricula/search/buscarAlunoEDisciplina']: {
    /**
     * executeSearch-matriculabean-get
     */
    'get'(
      parameters?: Parameters<Paths.ExecuteSearchMatriculabeanGet.QueryParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.ExecuteSearchMatriculabeanGet.Responses.$200>
  }
  ['/api/matricula/search/buscarTodasDisciplinasPorAluno']: {
    /**
     * executeSearch-matriculabean-get_1
     */
    'get'(
      parameters?: Parameters<Paths.ExecuteSearchMatriculabeanGet1.QueryParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.ExecuteSearchMatriculabeanGet1.Responses.$200>
  }
  ['/api/matricula/search/buscarTodasMatriculasPorDisciplina']: {
    /**
     * executeSearch-matriculabean-get_2
     */
    'get'(
      parameters?: Parameters<Paths.ExecuteSearchMatriculabeanGet2.QueryParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.ExecuteSearchMatriculabeanGet2.Responses.$200>
  }
  ['/api/matricula/{id}']: {
    /**
     * getItemResource-matriculabean-get - get-matriculabean
     */
    'get'(
      parameters?: Parameters<Paths.GetItemResourceMatriculabeanGet.PathParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.GetItemResourceMatriculabeanGet.Responses.$200>
    /**
     * putItemResource-matriculabean-put - update-matriculabean
     */
    'put'(
      parameters?: Parameters<Paths.PutItemResourceMatriculabeanPut.PathParameters> | null,
      data?: Paths.PutItemResourceMatriculabeanPut.RequestBody,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.PutItemResourceMatriculabeanPut.Responses.$200 | Paths.PutItemResourceMatriculabeanPut.Responses.$201 | Paths.PutItemResourceMatriculabeanPut.Responses.$204>
    /**
     * deleteItemResource-matriculabean-delete - delete-matriculabean
     */
    'delete'(
      parameters?: Parameters<Paths.DeleteItemResourceMatriculabeanDelete.PathParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.DeleteItemResourceMatriculabeanDelete.Responses.$204>
    /**
     * patchItemResource-matriculabean-patch - patch-matriculabean
     */
    'patch'(
      parameters?: Parameters<Paths.PatchItemResourceMatriculabeanPatch.PathParameters> | null,
      data?: Paths.PatchItemResourceMatriculabeanPatch.RequestBody,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.PatchItemResourceMatriculabeanPatch.Responses.$200 | Paths.PatchItemResourceMatriculabeanPatch.Responses.$204>
  }
  ['/api/profile']: {
    /**
     * listAllFormsOfMetadata_1
     */
    'get'(
      parameters?: Parameters<UnknownParamsObject> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.ListAllFormsOfMetadata1.Responses.$200>
  }
  ['/api/profile/disciplina']: {
    /**
     * descriptor_1_1_1
     */
    'get'(
      parameters?: Parameters<UnknownParamsObject> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.Descriptor111.Responses.$200>
  }
  ['/api/profile/matricula']: {
    /**
     * descriptor_1_1_2
     */
    'get'(
      parameters?: Parameters<UnknownParamsObject> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.Descriptor112.Responses.$200>
  }
  ['/matricula/{idAluno}/{idDisciplina}']: {
    /**
     * matricular
     */
    'put'(
      parameters?: Parameters<Paths.Matricular.PathParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.Matricular.Responses.$200>
    /**
     * cancelar
     */
    'delete'(
      parameters?: Parameters<Paths.Cancelar.PathParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.Cancelar.Responses.$200>
  }
  ['/aluno/{id}']: {
    /**
     * getAlunoByPath - Obter dados aluno
     * 
     * Obtem os dados de um aluno pelo código
     */
    'get'(
      parameters?: Parameters<Paths.GetAlunoByPath.PathParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.GetAlunoByPath.Responses.$200>
    /**
     * atualizar
     */
    'put'(
      parameters?: Parameters<Paths.Atualizar.PathParameters> | null,
      data?: Paths.Atualizar.RequestBody,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.Atualizar.Responses.$200>
    /**
     * remover
     */
    'delete'(
      parameters?: Parameters<Paths.Remover.PathParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.Remover.Responses.$200>
  }
  ['/aluno']: {
    /**
     * getAluno
     */
    'get'(
      parameters?: Parameters<Paths.GetAluno.QueryParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.GetAluno.Responses.$200>
    /**
     * cadastrar
     */
    'post'(
      parameters?: Parameters<UnknownParamsObject> | null,
      data?: Paths.Cadastrar.RequestBody,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.Cadastrar.Responses.$200>
  }
  ['/faculdade/disciplinas/{idAluno}']: {
    /**
     * disciplinasAluno
     */
    'get'(
      parameters?: Parameters<Paths.DisciplinasAluno.PathParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.DisciplinasAluno.Responses.$200>
  }
  ['/faculdade/carga/{idAluno}']: {
    /**
     * cargaHoraria
     */
    'get'(
      parameters?: Parameters<Paths.CargaHoraria.PathParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.CargaHoraria.Responses.$200>
  }
  ['/aluno/todos']: {
    /**
     * todosAlunos - Lista alunos
     * 
     * Obtem a lista de todos os alunos
     */
    'get'(
      parameters?: Parameters<UnknownParamsObject> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.TodosAlunos.Responses.$200>
  }
  ['/aluno/nome/{nome}']: {
    /**
     * todosAlunosPorNome
     */
    'get'(
      parameters?: Parameters<Paths.TodosAlunosPorNome.PathParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.TodosAlunosPorNome.Responses.$200>
  }
  ['/aluno/curso/{curso}']: {
    /**
     * todosAlunosPorCurso
     */
    'get'(
      parameters?: Parameters<Paths.TodosAlunosPorCurso.PathParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.TodosAlunosPorCurso.Responses.$200>
  }
  ['/aluno/curso/{curso}/{turma}']: {
    /**
     * todosAlunosPorCursoTurma
     */
    'get'(
      parameters?: Parameters<Paths.TodosAlunosPorCursoTurma.PathParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.TodosAlunosPorCursoTurma.Responses.$200>
  }
  ['/faculdade/disciplina/{idDisciplina}']: {
    /**
     * removerDisciplina
     */
    'delete'(
      parameters?: Parameters<Paths.RemoverDisciplina.PathParameters> | null,
      data?: any,
      config?: AxiosRequestConfig  
    ): OperationResponse<Paths.RemoverDisciplina.Responses.$200>
  }
}

export type Client = OpenAPIClient<OperationMethods, PathsDictionary>
